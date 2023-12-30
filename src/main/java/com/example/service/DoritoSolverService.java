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

    public static int SOLVED = 0;
    public List<DoritoGame> solveDoritoGame(DoritoGame doritoGame) {

        System.out.println("zdtnow1:" + ZonedDateTime.now());
        List<DoritoGame> solveDoritoGames = new ArrayList();

        doritoGame.getBoxes()[0][0].setColor(4); //green
        solver(0, 0, doritoGame, solveDoritoGames);

        System.out.println("zdtnow2:" + ZonedDateTime.now());
        System.out.println("solveDoritoGames.size:" + solveDoritoGames.size());
        return solveDoritoGames;
    }

    private void solver(int prevRow, int prevCol, DoritoGame doritoGame, List<DoritoGame> solveDoritoGames) {

            if ((prevRow == doritoGame.getNrOfRows() - 1) && (prevCol == doritoGame.getNrOfColumns() - 1)) {
                //Solution found
                if (isAcceptedDoritoGame(doritoGame)) {
                    solveDoritoGames.add(doritoGame);
                    SOLVED++;
                    System.out.println("SOLVED=" + SOLVED);
                } else {
                    //doritoGame = null;
                }
                return;
            }

            goingUp(prevRow, prevCol, doritoGame, solveDoritoGames);
            goingRight(prevRow, prevCol, doritoGame, solveDoritoGames);
            goingDown(prevRow, prevCol, doritoGame, solveDoritoGames);
            goingLeft(prevRow, prevCol, doritoGame, solveDoritoGames);
    }

    private void goingUp(int prevRow, int prevCol, DoritoGame doritoGame, List<DoritoGame> solveDoritoGames) {

        if (prevRow == doritoGame.getNrOfRows() - 1) { //going to hit roof
            return;
        }

        if (1 == doritoGame.getBoxes()[prevRow+1][prevCol].getColor() || 4 == doritoGame.getBoxes()[prevRow+1][prevCol].getColor()) {
            return;
        }

        DoritoGame newDoritoGame = copyOldGame(doritoGame);
        newDoritoGame.getBoxes()[prevRow+1][prevCol].setColor(4); //4 green = visited

        solver(prevRow+1, prevCol, newDoritoGame, solveDoritoGames);
    }

    private void goingDown(int prevRow, int prevCol, DoritoGame doritoGame, List<DoritoGame> solveDoritoGames) {
        //System.out.println("down");
        if (prevRow == 0) { //going to hit floor
            return;
        }

        if (1 == doritoGame.getBoxes()[prevRow-1][prevCol].getColor() || 4 == doritoGame.getBoxes()[prevRow-1][prevCol].getColor()) {
            return;
        }

        DoritoGame newDoritoGame = copyOldGame(doritoGame);
        newDoritoGame.getBoxes()[prevRow-1][prevCol].setColor(4); //4 green = visited

        solver(prevRow-1, prevCol, newDoritoGame, solveDoritoGames);
    }

    private void goingRight(int prevRow, int prevCol, DoritoGame doritoGame, List<DoritoGame> solveDoritoGames) {
        //System.out.println("right");

        if (prevCol == doritoGame.getNrOfColumns() - 1) { //going to hit right side
            return;
        }

        if (1 == doritoGame.getBoxes()[prevRow][prevCol + 1].getColor() || 4 == doritoGame.getBoxes()[prevRow][prevCol + 1].getColor()) {
            return;
        }

        DoritoGame newDoritoGame = copyOldGame(doritoGame);
        newDoritoGame.getBoxes()[prevRow][prevCol + 1].setColor(4); //4 green = visited

        solver(prevRow, prevCol + 1, newDoritoGame, solveDoritoGames);
    }

    private void goingLeft(int prevRow, int prevCol, DoritoGame doritoGame, List<DoritoGame> solveDoritoGames) {
        //System.out.println("left");

        if (prevCol == 0) { //going to hit left side
            return;
        }

        if (1 == doritoGame.getBoxes()[prevRow][prevCol - 1].getColor() || 4 == doritoGame.getBoxes()[prevRow][prevCol - 1].getColor()) {
            return;
        }

        DoritoGame newDoritoGame = copyOldGame(doritoGame);

        newDoritoGame.getBoxes()[prevRow][prevCol - 1].setColor(4); //4 green = visited

        solver(prevRow, prevCol - 1, newDoritoGame, solveDoritoGames);
    }

    private DoritoGame copyOldGame(DoritoGame oldDoritoGame) {

        DoritoGame newDoritoGame = new DoritoGame();
        newDoritoGame.setNrOfBlackBoxes(oldDoritoGame.getNrOfBlackBoxes());
        newDoritoGame.setNrOfRows(oldDoritoGame.getNrOfRows());
        newDoritoGame.setNrOfColumns(oldDoritoGame.getNrOfColumns());
        Box[][] newBoxes = new Box[oldDoritoGame.getNrOfRows()][oldDoritoGame.getNrOfColumns()];

        for (int i = 0; i < oldDoritoGame.getNrOfRows(); i++) {
            for (int k = 0; k < oldDoritoGame.getNrOfColumns(); k++) {
                Box box = new Box();
                box.setColor(oldDoritoGame.getBoxes()[i][k].getColor());
                box.setNrOfTriangles(oldDoritoGame.getBoxes()[i][k].getNrOfTriangles());
                newBoxes[i][k] = box;
            }
            newDoritoGame.setBoxes(newBoxes);
        }
        oldDoritoGame = null;
        return newDoritoGame;
    }

    private boolean isAcceptedDoritoGame(DoritoGame doritoGame) {
        for (int i = 0; i < doritoGame.getNrOfRows(); i++) {
            for (int k = 0; k < doritoGame.getNrOfColumns(); k++) {
                if (1 == doritoGame.getBoxes()[i][k].getColor()) { //black
                    if (doritoGame.getBoxes()[i][k].getNrOfTriangles() > 0) {

                        /*
                        funkar inte??
                        int visit1 = doritoGame.getBoxes()[i-1][k].isVisited() ? 1 : 0;
                        int visit2 = doritoGame.getBoxes()[i+1][k].isVisited() ? 1 : 0;
                        int visit3 = doritoGame.getBoxes()[i][k+1].isVisited() ? 1 : 0;
                        int visit4 = doritoGame.getBoxes()[i][k+1].isVisited() ? 1 : 0;
                        int sum = visit1 + visit2 + visit3 + visit4;
                        if (sum != doritoGame.getBoxes()[i][k].getNrOfTriangles()) {
                            return false;
                        }
                        */

            //            int visit1 = "green".equals(doritoGame.getBoxes()[i-1][k].getColor()) ? 1 : 0; //under
            //            int visit2 = "green".equals(doritoGame.getBoxes()[i+1][k].getColor()) ? 1 : 0; //over
            //            int visit3 = "green".equals(doritoGame.getBoxes()[i][k-1].getColor()) ? 1 : 0; //left
            //            int visit4 = "green".equals(doritoGame.getBoxes()[i][k+1].getColor()) ? 1 : 0; //right

                        int visit1 = 4 == doritoGame.getBoxes()[i-1][k].getColor() ? 1 : 0; //under
                        int visit2 = 4 == doritoGame.getBoxes()[i+1][k].getColor() ? 1 : 0; //over
                        int visit3 = 4 == doritoGame.getBoxes()[i][k-1].getColor() ? 1 : 0; //left
                        int visit4 = 4 == doritoGame.getBoxes()[i][k+1].getColor() ? 1 : 0; //right

                        int sum = visit1 + visit2 + visit3 + visit4;
                        if (sum != doritoGame.getBoxes()[i][k].getNrOfTriangles()) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
