package com.example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DoritoGame {

    private int nrOfBlackBoxes;
    private int nrOfRows;
    private int nrOfColumns;
    private Box[][] boxes;

    public DoritoGame(int nrOfBlackBoxes) {

        this.setNrOfBlackBoxes(nrOfBlackBoxes);
        this.setNrOfColumns((int)((Math.sqrt(nrOfBlackBoxes) * 2) + 1));
        this.setNrOfRows((int)((Math.sqrt(nrOfBlackBoxes) * 2) + 1));

        System.out.println("this.getNrOfColumns:" + this.getNrOfColumns());

        Box[][] boxes = new Box[this.getNrOfRows()][this.getNrOfColumns()];

        for (int i = 0; i < this.getNrOfRows(); i++) {
            for (int k = 0; k < this.getNrOfColumns(); k++) {
                Box box = new Box();
                box.setColor(initBoxColor(i, k));
                box.setNrOfTriangles(initNrOfTrianlges(i, k));
                boxes[i][k] = box;
            }
        }

        this.setBoxes(boxes);
    }

    private int initNrOfTrianlges(int i, int k) {

        /* %ellt lätt/med/svårt
            List<Integer> easy = List.of(15, 6, 3); //  1,2,3 trianlges
    List<Integer> medium = List.of(20, 8, 6); // 1,2,3 trianlges
    List<Integer> hard = List.of(24, 12, 8); // 1,2,3 trianlges
         */
        if (i % 2 != 0 && k % 2 != 0) {

            boolean theDifficult = false;
            boolean allZero = false;
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

    private int initBoxColor(int i, int k) {
        if (i % 2 != 0 && k % 2 != 0) {
            return 1;
            //return "black";
        }
        if (i % 2 == 0 && k % 2 == 0) {
            return 2;
            //return "grey"; //beslutsruta
        }
        return 3;
        //return "coral"; //faktiska strecken
    }
}