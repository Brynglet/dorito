package com.example.service;

import com.example.domain.Box;
import com.example.domain.DoritoGame;
import com.example.domain.DoritoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DoritoService {

    public DoritoResponse getDoritoResponse(int nrOfBlackBoxes) {
        DoritoGame doritoGame = createDoritoGame(nrOfBlackBoxes);
        System.out.println(doritoGame);
        DoritoResponse doritoResponse = new DoritoResponse();
        doritoResponse.setRespString("RESPSTR!");
        return doritoResponse;
    }

    private DoritoGame createDoritoGame(int nrOfBlackBoxes) {

        DoritoGame doritoGame = new DoritoGame();

        int rows = (nrOfBlackBoxes * 2) + 1;
        int columns = rows;
        Box[][] boxes = new Box[rows][columns];

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

        doritoGame.setBoxes(boxes);
        doritoGame.setNrOfBlackBoxes(nrOfBlackBoxes);
        doritoGame.setSolutions(null); //todo
        return doritoGame;
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
