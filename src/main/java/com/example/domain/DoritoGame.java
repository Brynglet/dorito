package com.example.domain;

import com.example.exception.ApiError;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Data
@Slf4j
@NoArgsConstructor
public class DoritoGame {

    private int nrOfBlackBoxesInt;
    private int nrOfRows;
    private int nrOfColumns;
    private Box[][] boxes;

    public DoritoGame(String nrOfBlackBoxesStr) {

        Common.validateInput(nrOfBlackBoxesStr);

        this.setNrOfBlackBoxesInt(Integer.parseInt(nrOfBlackBoxesStr));
        this.setNrOfRows((int)((Math.sqrt(this.getNrOfBlackBoxesInt()) * 2) + 1));
        this.setNrOfColumns((int)((Math.sqrt(this.getNrOfBlackBoxesInt()) * 2) + 1));

        log.info("this.getNrOfColumns:" + this.getNrOfColumns());

        Box[][] boxes = new Box[this.getNrOfRows()][this.getNrOfColumns()];

        for (int r = 0; r < this.getNrOfRows(); r++) {
            for (int c = 0; c < this.getNrOfColumns(); c++) {
                Box box = new Box();
                box.setColorEnum(initBoxColor(r, c));
                box.setNrOfTriangles(initNrOfTriangles(r, c));
                boxes[r][c] = box;
            }
        }

        this.setBoxes(boxes);
    }

    private int initNrOfTriangles(int r, int c) {

        boolean theDifficult = false;
        boolean theDifficult2 = true;
        boolean allZeros = false;
        boolean randomize = false;

        if (theDifficult) {
            return getTheDifficult(r, c);
        } else if (theDifficult2) {
            return getTheDifficult2();
        } else if (allZeros) {
            return getAllZeros();
        }  else if (randomize) {
            return getRandom(r, c);
        } else {
            throw new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Must chose a blackBoxTemplate");
        }
    }

    //Just a diffult one to solve for a human
    private int getTheDifficult(int r, int c) {

        if (r % 2 != 0 && c % 2 != 0) {

            if (r == 1 && c == 1) {
                return 2;
            }

            if (r == 7 && c == 1) {
                return 2;
            }

            if (r == 3 && c == 3) {
                return 1;
            }

            if (r == 5 && c == 3) {
                return 1;
            }

            if (r == 7 && c == 3) {
                return 1;
            }

            if (r == 1 && c == 7) {
                return 2;
            }

            if (r == 5 && c == 7) {
                return 2;
            }

            if (r == 7 && c == 7) {
                return 2;
            }

        }
        return 0;
    }

    private int getTheDifficult2(int r, int c) {

        if (r % 2 != 0 && c % 2 != 0) {

            ...fixa

        }
        return 0;
    }

    private int getAllZeros() {
        return 0;
    }

    private int getRandom(int r, int c) {
        if (r % 2 != 0 && c % 2 != 0) {
            // Randomize
            int randNumber = (int) (Math.random() * 100); //0-99

            if (randNumber < 23) {
                return 1;
            } else if (randNumber < 29) {
                return 2;
            } else if (randNumber < 34) {
                return 3;
            }
        }
        return 0;
    }

    private ColorEnum initBoxColor(int r, int c) {
        if (r % 2 != 0 && c % 2 != 0) {
            return ColorEnum.BLACK;
        }
        if (r % 2 == 0 && c % 2 == 0) {
            return ColorEnum.GREY;
        }
        return ColorEnum.CORAL;
    }

    public void clear() {
        this.setBoxes(null);
        this.setNrOfBlackBoxesInt(0);
        this.setNrOfRows(0);
        this.setNrOfColumns(0);
    }
}