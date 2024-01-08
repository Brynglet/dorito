package com.example.service;

import com.example.domain.Box;
import com.example.domain.ColorEnum;
import com.example.domain.DoritoGame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DoritoSolverService {

    public static Long RECURSION_COUNT = 0L;
    public static Long SOLUTION_COUNT = 0L;
    public List<DoritoGame> solveDoritoGame(DoritoGame doritoGame) {

        RECURSION_COUNT = 0L;

        ZonedDateTime before = ZonedDateTime.now();

        List<DoritoGame> solvedDoritoGames = new ArrayList<>();

        //Recursion start
        doStep(0, 0, doritoGame);
        //Recursion end

        Long durationMs = ZonedDateTime.now().toInstant().toEpochMilli() - before.toInstant().toEpochMilli();

        log.info("recursion time ms:" + durationMs);

        log.info("recursion time:" + (durationMs.doubleValue() / 1000) + " seconds");

        log.info("SOLUTION_COUNT:" + SOLUTION_COUNT);
        log.info("RECURSION_COUNT:" + RECURSION_COUNT);
        return solvedDoritoGames;
    }

    private void doStep(int newRow, int newCol, DoritoGame doritoGame) {

        if (SOLUTION_COUNT > 0) return; // Found at least 1 solution

        RECURSION_COUNT++;

        //black or visited
        if (ColorEnum.BLACK.equals(doritoGame.getBoxes()[newRow][newCol].getColorEnum()) || ColorEnum.GREEN.equals(doritoGame.getBoxes()[newRow][newCol].getColorEnum())) {
            return;
        }

        DoritoGame newDoritoGame = copyOldGame(doritoGame);
        newDoritoGame.getBoxes()[newRow][newCol].setColorEnum(ColorEnum.GREEN);

        if (isAtEndPoint(newDoritoGame, newRow, newCol)) {
            //Possible solution found
            if (isAcceptedDoritoGame(newDoritoGame)) {
                SOLUTION_COUNT++;
            }
            return;
        }

        if (blackSumIsTooHigh(newDoritoGame, newRow, newCol)) {
            return;
        }

        if (canGoUp(newDoritoGame, newRow, newCol)) {
            doStep(newRow+1, newCol, newDoritoGame);
        }
        if (canGoRight(newDoritoGame, newRow, newCol)) {
            doStep(newRow, newCol+1, newDoritoGame);
        }
        if (canGoLeft(newDoritoGame, newRow, newCol)) {
            doStep(newRow, newCol-1, newDoritoGame);
        }
        if (canGoDown(newDoritoGame, newRow, newCol)) {
            doStep(newRow-1, newCol, newDoritoGame);
        }
        newDoritoGame.clear();
        newDoritoGame = null;
    }

    private boolean canGoUp(DoritoGame newDoritoGame, int newRow, int newCol) {
        boolean nextnextGreen = (newRow+2 < newDoritoGame.getNrOfRows() && ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow+2][newCol].getColorEnum()));
        return !nextnextGreen && (newRow+1 < newDoritoGame.getNrOfRows()) && (!ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow+1][newCol].getColorEnum()));
    }

    private boolean canGoRight(DoritoGame newDoritoGame, int newRow, int newCol) {
        boolean nextnextGreen = (newCol+2 < newDoritoGame.getNrOfColumns()) && (ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow][newCol+2].getColorEnum()));
        return !nextnextGreen && (newCol+1 < newDoritoGame.getNrOfColumns()) && (!ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow][newCol+1].getColorEnum()));
    }

    private boolean canGoLeft(DoritoGame newDoritoGame, int newRow, int newCol) {
        boolean nextnextGreen = (newCol-2 > -1) && (ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow][newCol-2].getColorEnum()));
        return !nextnextGreen && (newCol-1 > -1) && (!ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow][newCol-1].getColorEnum()));
    }

    private boolean canGoDown(DoritoGame newDoritoGame, int newRow, int newCol) {
        boolean nextnextGreen = (newRow-2 > -1) && (ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow-2][newCol].getColorEnum()));
        return !nextnextGreen && (newRow-1 > -1) && (!ColorEnum.GREEN.equals(newDoritoGame.getBoxes()[newRow-1][newCol].getColorEnum()));
    }

    private boolean isAtEndPoint(DoritoGame newDoritoGame, int newRow, int newCol) {
        return (newRow == newDoritoGame.getNrOfRows() - 1) && (newCol == newDoritoGame.getNrOfColumns() - 1);
    }

    private DoritoGame copyOldGame(DoritoGame oldDoritoGame) {

        DoritoGame newDoritoGame = new DoritoGame();
        newDoritoGame.setNrOfBlackBoxesInt(oldDoritoGame.getNrOfBlackBoxesInt());
        newDoritoGame.setNrOfRows(oldDoritoGame.getNrOfRows());
        newDoritoGame.setNrOfColumns(oldDoritoGame.getNrOfColumns());
        //newDoritoGame.setBoxes(oldDoritoGame.getBoxes()); No because reference affects old with setting it to green
        Box[][] newBoxes = new Box[oldDoritoGame.getNrOfRows()][oldDoritoGame.getNrOfColumns()];
        Box box;
        for (int i = 0; i < oldDoritoGame.getNrOfRows(); i++) {
            for (int k = 0; k < oldDoritoGame.getNrOfColumns(); k++) {
                box = new Box();
                box.setColorEnum(oldDoritoGame.getBoxes()[i][k].getColorEnum());
                box.setNrOfTriangles(oldDoritoGame.getBoxes()[i][k].getNrOfTriangles());
                newBoxes[i][k] = box;
            }
            newDoritoGame.setBoxes(newBoxes);
        }

        //oldDoritoGame.clear(); This causes npe!
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
                if (ColorEnum.BLACK.equals(doritoGame.getBoxes()[i][k].getColorEnum())) {
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
        //System.out.println("Solved!!!!!!");
        return true;
    }

}
