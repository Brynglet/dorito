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

    public List<DoritoGame> solveDoritoGame(DoritoGame doritoGame) {

        ZonedDateTime before = ZonedDateTime.now();
        List<DoritoGame> solvedDoritoGames = new ArrayList();

        doStep(1, 0, 0, doritoGame, solvedDoritoGames);

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
    private void doStep(int commingFrom, int newRow, int newCol, DoritoGame doritoGame, List<DoritoGame> solvedDoritoGames) {

        //black or visited
        if (1 == doritoGame.getBoxes()[newRow][newCol].getColor() || 4 == doritoGame.getBoxes()[newRow][newCol].getColor()) {
            return;
        }

        if (commingFrom == 1) { //came from down

            DoritoGame newDoritoGame = copyOldGame(doritoGame);
            newDoritoGame.getBoxes()[newRow][newCol].setColor(4);

            if ((newRow == newDoritoGame.getNrOfRows() - 1) && (newCol == newDoritoGame.getNrOfColumns() - 1)) {
                //possible Solution found
                if (isAcceptedDoritoGame(newDoritoGame)) {
                    solvedDoritoGames.add(newDoritoGame);
                }
                return;
            }

            if (blackSumIsTooHigh(newDoritoGame, newRow, newCol)) {
                return;
            }

    /*
    1 underifrån
	2 vänsterifrån
	3 överifrån
	4 högerifrån
     */
            if (newRow+1 < newDoritoGame.getNrOfRows()) doStep(1,newRow+1, newCol, newDoritoGame, solvedDoritoGames); //gå uppåt
            if (newCol+1 < newDoritoGame.getNrOfColumns()) doStep(2,newRow, newCol+1, newDoritoGame, solvedDoritoGames); //gå höger
            //go(3,newRow-1, newCol, newDoritoGame, solvedDoritoGames); //gå neråt
            if (newCol-1 > -1) doStep(4,newRow, newCol-1, newDoritoGame, solvedDoritoGames); //gå vänster

        } else if (commingFrom == 3) { //came from up

            DoritoGame newDoritoGame = copyOldGame(doritoGame);

            newDoritoGame.getBoxes()[newRow][newCol].setColor(4);
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
    /*
    1 underifrån
	2 vänsterifrån
	3 överifrån
	4 högerifrån
     */
            //go(1,newRow+1, newCol, newDoritoGame, solvedDoritoGames); //gå uppåt
            if (newCol+1 < newDoritoGame.getNrOfColumns()) doStep(2,newRow, newCol+1, newDoritoGame, solvedDoritoGames); //gå höger
            if (newRow-1 > -1) doStep(3,newRow-1, newCol, newDoritoGame, solvedDoritoGames); //gå neråt
            if (newCol-1 > -1) doStep(4,newRow, newCol-1, newDoritoGame, solvedDoritoGames); //gå vänster

        } else if (commingFrom == 2) { //came from left

            DoritoGame newDoritoGame = copyOldGame(doritoGame);

            newDoritoGame.getBoxes()[newRow][newCol].setColor(4);
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
    /*
    1 underifrån
	2 vänsterifrån
	3 överifrån
	4 högerifrån
     */
            if (newRow+1 < newDoritoGame.getNrOfRows()) doStep(1,newRow+1, newCol, newDoritoGame, solvedDoritoGames); //gå uppåt

            if (newCol+1 < newDoritoGame.getNrOfColumns()) doStep(2,newRow, newCol+1, newDoritoGame, solvedDoritoGames); //gå höger
            if (newRow-1 > -1) doStep(3,newRow-1, newCol, newDoritoGame, solvedDoritoGames); //gå neråt
           // go(4,newRow, newCol-1, newDoritoGame, solvedDoritoGames); //gå vänster

        } else if (commingFrom == 4) { //came from right

            DoritoGame newDoritoGame = copyOldGame(doritoGame);

            newDoritoGame.getBoxes()[newRow][newCol].setColor(4);
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
    /*
    1 underifrån
	2 vänsterifrån
	3 överifrån
	4 högerifrån
     */
            if (newRow+1 < newDoritoGame.getNrOfRows()) doStep(1,newRow+1, newCol, newDoritoGame, solvedDoritoGames); //gå uppåt
            //go(2,newRow, newCol+1, newDoritoGame, solvedDoritoGames); //gå höger
            if (newRow-1 > -1) doStep(3,newRow-1, newCol, newDoritoGame, solvedDoritoGames); //gå neråt
            if (newCol-1 > -1) doStep(4,newRow, newCol-1, newDoritoGame, solvedDoritoGames); //gå vänster

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
                box.setColor(oldDoritoGame.getBoxes()[i][k].getColor());
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
                    int visit1 = 4 == newDoritoGame.getBoxes()[newRow][newCol - 2].getColor() ? 1 : 0; //leftside
                    int visit2 = 4 == newDoritoGame.getBoxes()[newRow][newCol].getColor() ? 1 : 0; //rightside curr
                    int visit3 = 4 == newDoritoGame.getBoxes()[newRow + 1][newCol - 1].getColor() ? 1 : 0; //up
                    int visit4 = 4 == newDoritoGame.getBoxes()[newRow - 1][newCol - 1].getColor() ? 1 : 0; //down
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
                    int visit1 = 4 == newDoritoGame.getBoxes()[newRow][newCol].getColor() ? 1 : 0; //leftside curr
                    int visit2 = 4 == newDoritoGame.getBoxes()[newRow][newCol + 2].getColor() ? 1 : 0; //rightside
                    int visit3 = 4 == newDoritoGame.getBoxes()[newRow + 1][newCol + 1].getColor() ? 1 : 0; //up
                    int visit4 = 4 == newDoritoGame.getBoxes()[newRow - 1][newCol + 1].getColor() ? 1 : 0; //down
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
                    int visit1 = 4 == newDoritoGame.getBoxes()[newRow+1][newCol-1].getColor() ? 1 : 0; //leftside
                    int visit2 = 4 == newDoritoGame.getBoxes()[newRow+1][newCol+1].getColor() ? 1 : 0; //rightside
                    int visit3 = 4 == newDoritoGame.getBoxes()[newRow + 2][newCol].getColor() ? 1 : 0; //up
                    int visit4 = 4 == newDoritoGame.getBoxes()[newRow][newCol].getColor() ? 1 : 0; //down curr
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
                    int visit1 = 4 == newDoritoGame.getBoxes()[newRow-1][newCol-1].getColor() ? 1 : 0; //leftside
                    int visit2 = 4 == newDoritoGame.getBoxes()[newRow-1][newCol+1].getColor() ? 1 : 0; //rightside
                    int visit3 = 4 == newDoritoGame.getBoxes()[newRow][newCol].getColor() ? 1 : 0; //up curr
                    int visit4 = 4 == newDoritoGame.getBoxes()[newRow-2][newCol].getColor() ? 1 : 0; //down
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
                if (1 == doritoGame.getBoxes()[i][k].getColor()) { //black
                    if (doritoGame.getBoxes()[i][k].getNrOfTriangles() > 0) {
                        int visit1 = 4 == doritoGame.getBoxes()[i-1][k].getColor() ? 1 : 0; //under
                        int visit2 = 4 == doritoGame.getBoxes()[i+1][k].getColor() ? 1 : 0; //over
                        int visit3 = 4 == doritoGame.getBoxes()[i][k-1].getColor() ? 1 : 0; //left
                        int visit4 = 4 == doritoGame.getBoxes()[i][k+1].getColor() ? 1 : 0; //right

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
