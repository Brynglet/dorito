package com.example.service;

import com.example.domain.Box;
import com.example.domain.DoritoGame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DoritoSolverService {
    public List<DoritoGame> allPathsDoritoGames(DoritoGame doritoGame) {

        System.out.println("zdtnow1:" + ZonedDateTime.now());
        List<DoritoGame> solveDoritoGames = new ArrayList();

        doritoGame.getBoxes()[0][0].setColor("green");
        doritoGame.getBoxes()[0][0].setVisited(true);
        solver(0, 0, doritoGame, solveDoritoGames);

        System.out.println("zdtnow2:" + ZonedDateTime.now());
        System.out.println("solveDoritoGames.size:" + solveDoritoGames.size());
        return solveDoritoGames;
    }

    private void solver(int prevCol, int prevRow, DoritoGame doritoGame, List<DoritoGame> solveDoritoGames) {

        if ((prevCol == doritoGame.getNrOfColumns() - 1) && (prevRow == doritoGame.getNrOfRows() - 1)) {
            //Solution found
            if (isAcceptedDoritoGame(doritoGame)) {
                solveDoritoGames.add(doritoGame);
            } else {
                doritoGame = null;
            }
            return;
        }

        goingUp(prevCol, prevRow, doritoGame, solveDoritoGames);
        goingRight(prevCol, prevRow, doritoGame, solveDoritoGames);
        goingDown(prevCol, prevRow, doritoGame, solveDoritoGames);
        goingLeft(prevCol, prevRow, doritoGame, solveDoritoGames);
    }

    private void goingUp(int prevCol, int prevRow, DoritoGame doritoGame, List<DoritoGame> solveDoritoGames) {

        if (prevRow == doritoGame.getNrOfRows() - 1) { //going to hit roof
            return;
        }

        if ("black".equalsIgnoreCase(doritoGame.getBoxes()[prevCol][prevRow + 1].getColor())) {
            return;
        }

        if (doritoGame.getBoxes()[prevCol][prevRow + 1].isVisited()) {
            return;
        }

        DoritoGame newDoritoGame = copyOldGame(doritoGame);
        newDoritoGame.getBoxes()[prevCol][prevRow + 1].setColor("green");
        newDoritoGame.getBoxes()[prevCol][prevRow + 1].setVisited(true);

        solver(prevCol, prevRow + 1, newDoritoGame, solveDoritoGames);
    }

    private void goingDown(int prevCol, int prevRow, DoritoGame doritoGame, List<DoritoGame> solveDoritoGames) {
        //System.out.println("down");
        if (prevRow == 0) { //going to hit floor
            return;
        }

        if ("black".equalsIgnoreCase(doritoGame.getBoxes()[prevCol][prevRow - 1].getColor())) {
            return;
        }

        if (doritoGame.getBoxes()[prevCol][prevRow - 1].isVisited()) {
            return;
        }

        DoritoGame newDoritoGame = copyOldGame(doritoGame);
        newDoritoGame.getBoxes()[prevCol][prevRow - 1].setColor("green");
        newDoritoGame.getBoxes()[prevCol][prevRow - 1].setVisited(true);

        solver(prevCol, prevRow - 1, newDoritoGame, solveDoritoGames);
    }

    private void goingRight(int prevCol, int prevRow, DoritoGame doritoGame, List<DoritoGame> solveDoritoGames) {
        //System.out.println("right");

        if (prevCol == doritoGame.getNrOfColumns() - 1) { //going to hit right side
            return;
        }

        if ("black".equalsIgnoreCase(doritoGame.getBoxes()[prevCol + 1][prevRow].getColor())) {
            return;
        }

        if (doritoGame.getBoxes()[prevCol + 1][prevRow].isVisited()) {
            return;
        }

        DoritoGame newDoritoGame = copyOldGame(doritoGame);
        newDoritoGame.getBoxes()[prevCol + 1][prevRow].setColor("green");
        newDoritoGame.getBoxes()[prevCol + 1][prevRow].setVisited(true);

        solver(prevCol + 1, prevRow, newDoritoGame, solveDoritoGames);
    }

    private void goingLeft(int prevCol, int prevRow, DoritoGame doritoGame, List<DoritoGame> solveDoritoGames) {
        //System.out.println("left");

        if (prevCol == 0) { //going to hit left side
            return;
        }

        if ("black".equalsIgnoreCase(doritoGame.getBoxes()[prevCol - 1][prevRow].getColor())) {
            return;
        }

        if (doritoGame.getBoxes()[prevCol - 1][prevRow].isVisited()) {
            return;
        }

        DoritoGame newDoritoGame = copyOldGame(doritoGame);

        newDoritoGame.getBoxes()[prevCol - 1][prevRow].setColor("green");
        newDoritoGame.getBoxes()[prevCol - 1][prevRow].setVisited(true);

        solver(prevCol - 1, prevRow, newDoritoGame, solveDoritoGames);
    }

    private DoritoGame copyOldGame(DoritoGame doritoGame) {

        DoritoGame newDoritoGame = new DoritoGame();
        newDoritoGame.setNrOfBlackBoxes(doritoGame.getNrOfBlackBoxes());
        newDoritoGame.setNrOfColumns(doritoGame.getNrOfColumns());
        newDoritoGame.setNrOfRows(doritoGame.getNrOfRows());
        Box[][] newBoxes = new Box[doritoGame.getNrOfColumns()][doritoGame.getNrOfRows()];

        for (int i = 0; i < doritoGame.getNrOfColumns(); i++) {
            for (int k = 0; k < doritoGame.getNrOfRows(); k++) {
                Box box = new Box();
                box.setVisited(doritoGame.getBoxes()[i][k].isVisited());
                box.setColor(doritoGame.getBoxes()[i][k].getColor());
                box.setNrOfTriangles(doritoGame.getBoxes()[i][k].getNrOfTriangles());
                newBoxes[i][k] = box;
            }
            newDoritoGame.setBoxes(newBoxes);
        }
        doritoGame = null;
        return newDoritoGame;
    }

    private boolean isAcceptedDoritoGame(DoritoGame doritoGame) {
        boolean ret = true;
        for (int i = 0; i < doritoGame.getNrOfColumns(); i++) {
            for (int k = 0; k < doritoGame.getNrOfRows(); k++) {
                if ("black".equals(doritoGame.getBoxes()[i][k].getColor())) {
                    if (doritoGame.getBoxes()[i][k].getNrOfTriangles() > 0) {
                        /* funkar inte??
                        int visit1 = doritoGame.getBoxes()[i-1][k].isVisited() ? 1 : 0;
                        int visit2 = doritoGame.getBoxes()[i+1][k].isVisited() ? 1 : 0;
                        int visit3 = doritoGame.getBoxes()[i][k+1].isVisited() ? 1 : 0;
                        int visit4 = doritoGame.getBoxes()[i][k+1].isVisited() ? 1 : 0;
                        int sum = visit1 + visit2 + visit3 + visit4;
                        if (sum != doritoGame.getBoxes()[i][k].getNrOfTriangles()) {
                            return false;
                        }
                         */

                        int visit1 = "green".equals(doritoGame.getBoxes()[i-1][k].getColor()) ? 1 : 0;
                        int visit2 = "green".equals(doritoGame.getBoxes()[i+1][k].getColor()) ? 1 : 0;
                        int visit3 = "green".equals(doritoGame.getBoxes()[i][k-1].getColor()) ? 1 : 0;
                        int visit4 = "green".equals(doritoGame.getBoxes()[i][k+1].getColor()) ? 1 : 0;

                        int sum = visit1 + visit2 + visit3 + visit4;
                        if (sum != doritoGame.getBoxes()[i][k].getNrOfTriangles()) {
                            ret = false;
                        }
                    }
                }
            }
        }
        return ret;
    }

}

