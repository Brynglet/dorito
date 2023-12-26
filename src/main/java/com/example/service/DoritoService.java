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
        DoritoResponse doritoResponse = new DoritoResponse();
        DoritoGame doritoGame = getTheGame(nrOfBlackBoxes);

        doritoResponse.setRespString(getResponseString(doritoGame));
        return doritoResponse;
    }

    private DoritoGame getTheGame(int nrOfBlackBoxes) {
        DoritoGame doritoGame = new DoritoGame();
        doritoGame.setNrOfBlackBoxes(nrOfBlackBoxes);

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
        doritoGame.setSolutions(null); //todo

        return doritoGame;
    }

    private String getTheColor(int i, int k) {

        if (i % 2 != 0 && k % 2 != 0) {
            return "black";
        }

        if (i % 2 == 0 && k % 2 == 0) {
            return "grey"; //beslutsruta
        }

        return "coral"; //faktiska strecken
    }

    private String getResponseString(DoritoGame doritoGame) {

        StringBuilder respSb = new StringBuilder();

        int rows = (doritoGame.getNrOfBlackBoxes() * 2) + 1;
        int columns = rows;

        Box [][] boxes = doritoGame.getBoxes();
        respSb.append("<table width=\"400px\" height=\"400px\" border=\"1\">");
        for (int i = rows-1; i >= 0; i--) {
            respSb.append("<tr>");
            for (int k = 0; k <columns ; k++) {

                String boxColor = "\"" + boxes[i][k].getColor() + "\"";
                //<td bgcolor="green">

                respSb.append("<td bgcolor=" + boxColor + "</td>");
            }
            respSb.append("</tr>");
        }
        respSb.append("</table>");

        return respSb.toString();
    }

}

