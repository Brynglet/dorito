package com.example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DoritoGame {
    private int nrOfBlackBoxes;
    private Box[][] boxes;
    List<String> solutions;
}