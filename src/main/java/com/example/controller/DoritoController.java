package com.example.controller;

import com.example.domain.DoritoGame;
import com.example.service.DoritoService;
import com.example.service.DoritoSolverService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static com.example.service.DoritoSolverService.RECURSION_COUNT;

@Slf4j
@Controller
public class DoritoController {

    private final DoritoService doritoService;
    private final DoritoSolverService doritoSolverService;

    @Autowired
    public DoritoController(DoritoService doritoService, DoritoSolverService doritoSolverService) {
        this.doritoService = doritoService;
        this.doritoSolverService = doritoSolverService;
    }

    @GetMapping("/dorito/{nrOfBlackBoxes}")
    public String theStart(HttpServletRequest request, @PathVariable(value = "nrOfBlackBoxes") String nrOfBlackBoxes) {
        try {
            RECURSION_COUNT = 0L;
            log.info("----------------------------------------");
            log.info("theStart " + nrOfBlackBoxes);
            DoritoGame initialDoritoGame = new DoritoGame(nrOfBlackBoxes);
            List<DoritoGame> solvedDoritoGames = doritoSolverService.solveDoritoGame(initialDoritoGame);
            String response = doritoService.getDoritoResponse(initialDoritoGame, solvedDoritoGames);
            request.setAttribute("doritoTables", response);
            return "dorito"; // Sends to dorito.jsp
        } catch (Exception e) {
            log.error("ERR theStart!:" +  e.getMessage());
            throw new RuntimeException("ajaxGames Error:" + e.getMessage());
        }
    }
}
