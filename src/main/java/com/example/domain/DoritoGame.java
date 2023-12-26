package com.example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DoritoGame {

    private int nrOfBlackBoxes;
    private int nrOfRows;
    private int nrOfColumns;
    private Box[][] boxes;
    List<String> solutions;

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
                boxes[i][k] = box;
            }
        }

        this.setBoxes(boxes);
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