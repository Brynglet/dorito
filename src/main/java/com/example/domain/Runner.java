package com.example.domain;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class Runner {

    private DoritoGame doritoGame;
    private List<String> steps;

    public Runner(DoritoGame doritoGame) {
        this.doritoGame = doritoGame;
    }
}
