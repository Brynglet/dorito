package com.example.service;

import com.example.domain.Box;
import com.example.domain.DoritoGame;
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

    public String getDoritoResponse(int nrOfBlackBoxes) {

        boolean isValid = isValid(nrOfBlackBoxes);

        if (!isValid) {
            return "Must be a sqaure nr >0 and <=36. Try again and best of luck.";
        }

        String respStr = StringUtils.EMPTY;

        DoritoGame initialDoritoGame = new DoritoGame(nrOfBlackBoxes);
        respStr += getResponseString(initialDoritoGame);

        List<DoritoGame> solvedList = doritoSolverService.solveDoritoGame(initialDoritoGame);

        respStr += "<br/><table><tr><td>Solutions:" + solvedList.size() + "</td></tr></table><br/>";

        if (ObjectUtils.isNotEmpty(solvedList)) {
            for (int k = 0; k < solvedList.size(); k++) {
                DoritoGame solvedDoritogame = solvedList.get(k);
                respStr += getResponseString(solvedDoritogame);
            }
        }

        respStr += "solutions:" + solvedList.size();

        return respStr;
    }

    private boolean isValid(int nrOfBlackBoxes) {

        if (nrOfBlackBoxes <= 0  || nrOfBlackBoxes > 36) {
            return false;
        }

        double rootExact = Math.sqrt(nrOfBlackBoxes);

        int rootAppr = (int) Math.sqrt(nrOfBlackBoxes);

        double res = rootExact - rootAppr;

        return (res == 0.0);
    }

    private String getResponseString(DoritoGame doritoGame) {

        StringBuilder respSb = new StringBuilder();

        Box [][] boxes = doritoGame.getBoxes();

        respSb.append("<table width=\"400px\" height=\"400px\" border=\"1\">");
        for (int i = doritoGame.getNrOfRows() - 1; i >= 0; i--) {
            respSb.append("<tr>");
            for (int k = 0; k < doritoGame.getNrOfColumns(); k++) {
                String theCol = "green";
                if (boxes[i][k].getColor() == 1) {
                    theCol = "black";
                } else   if (boxes[i][k].getColor() == 2) {
                    theCol = "grey";
                }   if (boxes[i][k].getColor() == 3) {
                    theCol = "coral";
                }
                String boxColor = "\"" + theCol + "\"";

                respSb.append("<td align=center valign=center style=\"color: yellow;\" bgcolor=" + boxColor + ">" + printTrianles(boxes[i][k], i, k) + "</td>");
            }
            respSb.append("</tr>");
        }

        respSb.append("</table>");

        return respSb.toString();
    }

    private String printTrianles(Box box, int col, int row) {
        if (col % 2 != 0 && row % 2 != 0) {
            if (box.getNrOfTriangles() == 0) {
                return "0";
            }
            if (box.getNrOfTriangles() == 1) {
                return "1";
            }
            if (box.getNrOfTriangles() == 2) {
                return "2";
            }
            if (box.getNrOfTriangles() == 3) {
                return "3";
            }
        }
        return "&nbsp&nbsp"; //for non black squares
    }
}

