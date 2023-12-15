package com.example.service;

import com.example.domain.DoritoGame;
import com.example.domain.DoritoResponse;
import lombok.Data;

@Data
public class DoritoSolver {

    private DoritoGame doritoGame;

    public DoritoSolver(DoritoGame doritoGame) {
        this.doritoGame = doritoGame;
    }

    public DoritoResponse solveDoritoGame() {

        System.out.println("doritoGame1=" + doritoGame);

        System.out.println("doritoGame2=" + doritoGame.toString());

        DoritoResponse doritoResponse = new DoritoResponse();
        doritoResponse.setRespString("hnhnunuhn");
        return doritoResponse;
    }

}
