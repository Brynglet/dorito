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

        this.setNrOfRows((int)((Math.sqrt(nrOfBlackBoxes) * 2) + 1));
        this.setNrOfColumns((int)((Math.sqrt(nrOfBlackBoxes) * 2) + 1));

        Box[][] boxes = new Box[nrOfRows][nrOfColumns];

        for (int i = 0; i < this.getNrOfRows(); i++) {
            // 4 blackboxes ger rows=9 ...=9 körningar
            for (int k = 0; k < this.getNrOfColumns(); k++) {
                Box box = new Box();
                box.setVisited(false);
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
            int randNumber = (int) (Math.random() * 100); //0-99

            if (randNumber < 20) {
                return 1;
            } else if (randNumber < 28) {
                return 2;
            } else if (randNumber < 38) {
                return 3;
            }

        }
        return 0;
    }

    private String initBoxColor(int i, int k) {
        if (i % 2 != 0 && k % 2 != 0) {
            return "black";
        }
        if (i % 2 == 0 && k % 2 == 0) {
            return "grey"; //beslutsruta
        }
        return "coral"; //faktiska strecken
    }
}