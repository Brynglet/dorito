package com.example.domain;

import lombok.Data;

import java.util.List;

@Data
public class DoritoGame {
    private int nrOfBlackBoxes;
    private Box[][] boxes;
    List<String> solutions;
}
