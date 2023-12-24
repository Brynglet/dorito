package com.example.domain;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Data
public class DoritoGame {

    private int nrOfBlackBoxes;
    private Box[][] boxes;
    List<String> solutions;


    public DoritoGame(int nrOfBlackBoxes) {
        this.nrOfBlackBoxes = nrOfBlackBoxes;
        int rows = (nrOfBlackBoxes * 2) + 1;
        int columns = rows;
        boxes = new Box[rows][rows];

        for (int i = 0; i < rows; i++) {
            // 4 blackboxes ger rows=9 ...=9 körningar

            for (int k = 0; k < columns; k++) {
                Box box = new Box();
                String color = getTheColor(i, k);
                box.setColor(color);
                box.setVisited(false);
                boxes[i][k] = box;
            }

        }

    }

    private String getTheColor(int i, int k) {
        if (i%2 != 0 && k%2 != 0) {
            return "black";
        }

        if (i%2 == 0 && k%2 == 0) {
            return "grey"; //beslutsruta
        }

        return "coral"; //faktiska strecken
    }
}
