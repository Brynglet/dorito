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

        boolean isValid = isValid(nrOfBlackBoxes);

        if (!isValid) {
            doritoResponse.setRespString("Must be a sqaure nr >0 and <= 100. Try again and best of luck.");
            return doritoResponse;
        }

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

    private boolean isValid(int nrOfBlackBoxes) {

        if (nrOfBlackBoxes <= 0  || nrOfBlackBoxes > 100) {
            return false;
        }

        double rootExact = Math.sqrt(nrOfBlackBoxes);

        int rootAppr = (int) Math.sqrt(nrOfBlackBoxes);

        double res = rootExact - rootAppr;

        return (res == 0.0);
    }

    private String getResponseString(DoritoGame doritoGame) {

        StringBuilder respSb = new StringBuilder();

        int rows = doritoGame.getNrOfRows();
        int columns = doritoGame.getNrOfColumns();

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

