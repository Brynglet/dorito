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
        DoritoGame doritoGame = new DoritoGame(nrOfBlackBoxes);

        doritoResponse.setRespString(getResponseString(doritoGame));
        return doritoResponse;
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

