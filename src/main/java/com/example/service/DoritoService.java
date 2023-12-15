package com.example.service;

import com.example.domain.DoritoGame;
import com.example.domain.DoritoResponse;
import com.example.domain.Filter;
import com.example.exception.ApiError;
import com.example.repository.DoritoRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.utility.Common.*;

@Slf4j
@Service
public class DoritoService {

    private final List<DoritoGame> allDoritoGames;

    @Autowired
    public DoritoService(DoritoRepository doritoRepository) throws IOException {
        /* Read all games into memory for better speed when filtering. */
        allDoritoGames = doritoRepository.getDoritos();

        DoritoGame doritoGame = allDoritoGames.get(0);


        DoritoSolver doritoSolver = new DoritoSolver(doritoGame);
        doritoSolver.solveDoritoGame();

    }
}
