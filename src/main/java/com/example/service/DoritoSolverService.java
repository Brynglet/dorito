package com.example.service;

import com.example.domain.DoritoGame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class DoritoSolverService {
    public List<DoritoGame> solveDoritoGames(DoritoGame doritoGame) {
        return Collections.emptyList();
    }
}

