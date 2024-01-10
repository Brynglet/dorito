package com.example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

        for (int i = 0; i < this.getNrOfRows(); i++) {
            for (int k = 0; k < this.getNrOfColumns(); k++) {
                Box box = new Box();
                box.setColorEnum(initBoxColor(i, k));
                box.setNrOfTriangles(initNrOfTriangles(i, k));
                boxes[i][k] = box;
            }
        }

        this.setBoxes(boxes);
    }

    private int initNrOfTriangles(int i, int k) {

        if (i % 2 != 0 && k % 2 != 0) {

            boolean theDifficult = false;
            boolean allZero = true;
            if (theDifficult) {
                if (i == 1 && k == 1) {
                    return 2;
                }

                if (i == 7 && k == 1) {
                    return 2;
                }

                if (i == 3 && k == 3) {
                    return 1;
                }

                if (i == 5 && k == 3) {
                    return 1;
                }

                if (i == 7 && k == 3) {
                    return 1;
                }

                if (i == 1 && k == 7) {
                    return 2;
                }

                if (i == 5 && k == 7) {
                    return 2;
                }

                if (i == 7 && k == 7) {
                    return 2;
                }

                if (true) return 0;
            }

            if (allZero) return 0;

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

    private ColorEnum initBoxColor(int i, int k) {
        if (i % 2 != 0 && k % 2 != 0) {
            return ColorEnum.BLACK;
        }
        if (i % 2 == 0 && k % 2 == 0) {
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