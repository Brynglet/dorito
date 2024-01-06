package com.example.service;

import com.example.domain.Box;
import com.example.domain.ColorEnum;
import com.example.domain.DirectionEnum;
import com.example.domain.DoritoGame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DoritoSolverService {

    public List<DoritoGame> solveDoritoGame(DoritoGame doritoGame) {

        ZonedDateTime before = ZonedDateTime.now();
        List<DoritoGame> solvedDoritoGames = new ArrayList();

        doStep(DirectionEnum.DOWN, 0, 0, doritoGame, solvedDoritoGames);

        Long durationMs = ZonedDateTime.now().toInstant().toEpochMilli() - before.toInstant().toEpochMilli();

        log.info("recursion time:" + (durationMs.doubleValue() / 1000) + " seconds");

        return solvedDoritoGames;
    }

    /*
    1 underifrån
	2 vänsterifrån
	3 överifrån
	4 högerifrån
     */
    private void doStep(DirectionEnum directionEnum, int newRow, int newCol, DoritoGame doritoGame, List<DoritoGame> solvedDoritoGames) {

        //black or visited
        if (ColorEnum.BLACK.equals(doritoGame.getBoxes()[newRow][newCol].getColorEnum()) || ColorEnum.GREEN.equals(doritoGame.getBoxes()[newRow][newCol].getColorEnum())) {
            return;
        }

        DoritoGame newDoritoGame = copyOldGame(doritoGame);
        newDoritoGame.getBoxes()[newRow][newCol].setColorEnum(ColorEnum.GREEN);

        if ((newRow == newDoritoGame.getNrOfRows() - 1) && (newCol == newDoritoGame.getNrOfColumns() - 1)) {
            //Solution found
            if (isAcceptedDoritoGame(newDoritoGame)) {
                solvedDoritoGames.add(newDoritoGame);
            }
            return;
        }

        if (blackSumIsTooHigh(newDoritoGame, newRow, newCol)) {
            return;
        }

        if (DirectionEnum.DOWN.equals(directionEnum)) { //came from down. No need to go down again.
            if (newRow+1 < newDoritoGame.getNrOfRows()) doStep(DirectionEnum.DOWN,newRow+1, newCol, newDoritoGame, solvedDoritoGames); //gå uppåt
            if (newCol+1 < newDoritoGame.getNrOfColumns()) doStep(DirectionEnum.LEFT,newRow, newCol+1, newDoritoGame, solvedDoritoGames); //gå höger
            if (newCol-1 > -1) doStep(DirectionEnum.RIGHT,newRow, newCol-1, newDoritoGame, solvedDoritoGames); //gå vänster
        } else if (DirectionEnum.UP.equals(directionEnum)) { //came from up. No need to go up again.
            if (newCol+1 < newDoritoGame.getNrOfColumns()) doStep(DirectionEnum.LEFT,newRow, newCol+1, newDoritoGame, solvedDoritoGames); //gå höger
            if (newRow-1 > -1) doStep(DirectionEnum.UP,newRow-1, newCol, newDoritoGame, solvedDoritoGames); //gå neråt
            if (newCol-1 > -1) doStep(DirectionEnum.RIGHT,newRow, newCol-1, newDoritoGame, solvedDoritoGames); //gå vänster
        } else if (DirectionEnum.LEFT.equals(directionEnum)) { //came from left. No go to check left again.
            if (newRow+1 < newDoritoGame.getNrOfRows()) doStep(DirectionEnum.DOWN,newRow+1, newCol, newDoritoGame, solvedDoritoGames); //gå uppåt
            if (newCol+1 < newDoritoGame.getNrOfColumns()) doStep(DirectionEnum.LEFT,newRow, newCol+1, newDoritoGame, solvedDoritoGames); //gå höger
            if (newRow-1 > -1) doStep(DirectionEnum.UP,newRow-1, newCol, newDoritoGame, solvedDoritoGames); //gå neråt
        } else if (DirectionEnum.RIGHT.equals(directionEnum)) { //came from right. No go to check right again.
            if (newRow+1 < newDoritoGame.getNrOfRows()) doStep(DirectionEnum.DOWN,newRow+1, newCol, newDoritoGame, solvedDoritoGames); //gå uppåt
            if (newRow-1 > -1) doStep(DirectionEnum.UP,newRow-1, newCol, newDoritoGame, solvedDoritoGames); //gå neråt
            if (newCol-1 > -1) doStep(DirectionEnum.RIGHT,newRow, newCol-1, newDoritoGame, solvedDoritoGames); //gå vänster
        }

    }

    private DoritoGame copyOldGame(DoritoGame oldDoritoGame) {

        DoritoGame newDoritoGame = new DoritoGame();
        newDoritoGame.setNrOfBlackBoxesInt(oldDoritoGame.getNrOfBlackBoxesInt());
        newDoritoGame.setNrOfRows(oldDoritoGame.getNrOfRows());
        newDoritoGame.setNrOfColumns(oldDoritoGame.getNrOfColumns());
        Box[][] newBoxes = new Box[oldDoritoGame.getNrOfRows()][oldDoritoGame.getNrOfColumns()];

        for (int i = 0; i < oldDoritoGame.getNrOfRows(); i++) {
            for (int k = 0; k < oldDoritoGame.getNrOfColumns(); k++) {
                Box box = new Box();
                box.setColorEnum(oldDoritoGame.getBoxes()[i][k].getColorEnum());
                box.setNrOfTriangles(oldDoritoGame.getBoxes()[i][k].getNrOfTriangles());
                newBoxes[i][k] = box;
            }
            newDoritoGame.setBoxes(newBoxes);
        }
        return newDoritoGame;
    }

    private boolean blackSumIsTooHigh(DoritoGame newDoritoGame, int newRow, int newCol) {

        if (newRow % 2 != 0 && newCol % 2 == 0) {
            //LEFT RIGHT BLACK BOX SITUATION

            //Left black box
            if (newCol > 1) {
                int nrOfTri = newDoritoGame.getBoxes()[newRow][newCol-1].getNrOfTriangles();
                if (nrOfTri > 0) {
                    int visit1 = ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow][newCol - 2].getColorEnum()) ? 1 : 0; //leftside
                    int visit2 = ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow][newCol].getColorEnum()) ? 1 : 0; //rightside curr
                    int visit3 = ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow + 1][newCol - 1].getColorEnum()) ? 1 : 0; //up
                    int visit4 = ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow - 1][newCol - 1].getColorEnum()) ? 1 : 0; //down
                    int sum = visit1 + visit2 + visit3 + visit4;
                    if (sum > nrOfTri) {
                        return true;
                    }
                }
            }

            //Right black box
            if (newCol < newDoritoGame.getNrOfColumns() - 1) {
                int nrOfTri = newDoritoGame.getBoxes()[newRow][newCol+1].getNrOfTriangles();
                if (nrOfTri > 0) {
                    int visit1 = ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow][newCol].getColorEnum()) ? 1 : 0; //leftside curr
                    int visit2 = ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow][newCol + 2].getColorEnum()) ? 1 : 0; //rightside
                    int visit3 = ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow + 1][newCol + 1].getColorEnum()) ? 1 : 0; //up
                    int visit4 = ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow - 1][newCol + 1].getColorEnum()) ? 1 : 0; //down
                    int sum = visit1 + visit2 + visit3 + visit4;
                    if (sum > nrOfTri) {
                        return true;
                    }
                }
            }
        }

        if (newRow % 2 == 0 && newCol % 2 != 0) {
            //UP DOWN SITUATION

            //Up black box
            if (newRow < newDoritoGame.getNrOfRows() - 1) {
                int nrOfTri = newDoritoGame.getBoxes()[newRow+1][newCol].getNrOfTriangles();
                if (nrOfTri > 0) {
                    int visit1 = ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow+1][newCol-1].getColorEnum()) ? 1 : 0; //leftside
                    int visit2 = ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow+1][newCol+1].getColorEnum()) ? 1 : 0; //rightside
                    int visit3 = ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow + 2][newCol].getColorEnum()) ? 1 : 0; //up
                    int visit4 = ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow][newCol].getColorEnum()) ? 1 : 0; //down curr
                    int sum = visit1 + visit2 + visit3 + visit4;
                    if (sum > nrOfTri) {
                        return true;
                    }
                }
            }

            //Down black box
            if (newRow > 1) {
                int nrOfTri = newDoritoGame.getBoxes()[newRow-1][newCol].getNrOfTriangles();
                if (nrOfTri > 0) {
                    int visit1 = ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow-1][newCol-1].getColorEnum()) ? 1 : 0; //leftside
                    int visit2 = ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow-1][newCol+1].getColorEnum()) ? 1 : 0; //rightside
                    int visit3 = ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow][newCol].getColorEnum()) ? 1 : 0; //up curr
                    int visit4 = ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow-2][newCol].getColorEnum()) ? 1 : 0; //down
                    int sum = visit1 + visit2 + visit3 + visit4;
                    if (sum > nrOfTri) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isAcceptedDoritoGame(DoritoGame doritoGame) {
        for (int i = 0; i < doritoGame.getNrOfRows(); i++) {
            for (int k = 0; k < doritoGame.getNrOfColumns(); k++) {
                if (ColorEnum.BLACK.equals(doritoGame.getBoxes()[i][k].getColorEnum())) { //black
                    if (doritoGame.getBoxes()[i][k].getNrOfTriangles() > 0) {
                        int visit1 = ColorEnum.GREEN.equals(doritoGame.getBoxes()[i-1][k].getColorEnum()) ? 1 : 0; //under
                        int visit2 = ColorEnum.GREEN.equals(doritoGame.getBoxes()[i+1][k].getColorEnum()) ? 1 : 0; //over
                        int visit3 = ColorEnum.GREEN.equals(doritoGame.getBoxes()[i][k-1].getColorEnum()) ? 1 : 0; //left
                        int visit4 = ColorEnum.GREEN.equals(doritoGame.getBoxes()[i][k+1].getColorEnum()) ? 1 : 0; //right

                        int sum = visit1 + visit2 + visit3 + visit4;
                        if (sum != doritoGame.getBoxes()[i][k].getNrOfTriangles()) {
                            //System.out.println("Not solved :(");
                            return false;
                        }
                    }
                }
            }
        }
        //System.out.println("Solved!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return true;
    }

}
