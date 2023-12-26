package com.example.service;

import com.example.domain.Box;
import com.example.domain.DoritoGame;
import com.example.domain.DoritoResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DoritoService {

    @Autowired
    private DoritoSolverService doritoSolverService;

    public DoritoResponse getDoritoResponse(int nrOfBlackBoxes) {

        DoritoResponse doritoResponse = new DoritoResponse();
        String respStr = StringUtils.EMPTY;

        DoritoGame initialDoritoGame = new DoritoGame(nrOfBlackBoxes);
        respStr += getResponseString(initialDoritoGame);

        List<DoritoGame> solvedDoritoGames = doritoSolverService.solveDoritoGames(initialDoritoGame);

        if (ObjectUtils.isNotEmpty(solvedDoritoGames)) {
            for (int k = 0; k < solvedDoritoGames.size(); k++) {
                DoritoGame solvedDoritogame = solvedDoritoGames.get(k);
                respStr += getResponseString(solvedDoritogame);
            }
        } else {
            respStr += "<br/><table><tr><td>NO SOLUTIONS</td></tr></table><br/>";
        }

        doritoResponse.setRespString(respStr);
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
                respSb.append("<td bgcolor=" + boxColor + "</td>");
            }
            respSb.append("</tr>");
        }

        respSb.append("</table>");

        return respSb.toString();
    }
}

