package com.example.service;

import com.example.domain.Box;
import com.example.domain.ColorEnum;
import com.example.domain.DoritoGame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.domain.Common.MAX_SOLUTIONS_PRINT;
import static com.example.service.DoritoSolverService.RECURSION_COUNT;
import static com.example.service.DoritoSolverService.SOLUTION_COUNT;

@Slf4j
@Service
public class DoritoService {

    public String getDoritoResponse(DoritoGame initialDoritoGame, List<DoritoGame> solvedDoritoGames) {

        StringBuilder sb = new StringBuilder();

        sb.append(getResponseString(initialDoritoGame));

        sb.append("<br/><table><tr><td>SOLUTION_COUNT:" + SOLUTION_COUNT + "</td></tr></table><br/>");

        sb.append("<br/><table><tr><td>RecursionCount:" + RECURSION_COUNT + "</td></tr></table><br/>");

        //sb.append("<br/><table><tr><td>Solutions:" + solvedDoritoGames.size() + "</td></tr></table><br/>");

        if (solvedDoritoGames.size() < MAX_SOLUTIONS_PRINT) {
            for (DoritoGame solvedDoritogame : solvedDoritoGames) {
                sb.append(getResponseString(solvedDoritogame));
            }
        }

        return sb.toString();
    }

    private String getResponseString(DoritoGame doritoGame) {

        StringBuilder respSb = new StringBuilder();

        Box [][] boxes = doritoGame.getBoxes();
        Box theBox;
        respSb.append("<table width=\"400px\" height=\"400px\" border=\"1\">");
        for (int i = doritoGame.getNrOfRows() - 1; i >= 0; i--) {
            respSb.append("<tr>");
            for (int k = 0; k < doritoGame.getNrOfColumns(); k++) {
                theBox = boxes[i][k];
                String boxColor = "\"" + theBox.getColorEnum().getColorEnumStr() + "\"";
                respSb.append("<td align=center valign=center style=\"color: yellow;\" bgcolor=");
                respSb.append(boxColor);
                respSb.append(">");
                respSb.append(ColorEnum.BLACK.equals(theBox.getColorEnum()) ? theBox.getNrOfTriangles() : "&nbsp;&nbsp;");
                respSb.append("</td>");
            }
            respSb.append("</tr>");
        }

        respSb.append("</table>");

        return respSb.toString();
    }

}

