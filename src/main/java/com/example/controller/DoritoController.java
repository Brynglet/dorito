package com.example.controller;

import com.example.domain.DoritoResponse;
import com.example.domain.Filter;
import com.example.service.DoritoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.ZonedDateTime;

@Slf4j
@Controller
public class DoritoController {

    private final DoritoService chessFishService;

    @Autowired
    public DoritoController(DoritoService chessFishService) {
        this.chessFishService = chessFishService;
    }

    /* Postman endpoint
    curl --location --request GET 'localhost:8088/games' \
        --header 'Content-Type: application/json' \
        --data '  {
        "color": "white",
        "lastName": "kasparov",
        "year" : "2011"
    }'
    */
    @GetMapping("/games")
    public ResponseEntity<String> getGames(@RequestBody @Valid Filter filter) {
        log.info("postman-> zdt;filter:" + ZonedDateTime.now() + filter.toString());
        var result = chessFishService.getPostmanResult(filter);
        return ResponseEntity.ok(result);
    }

    /*  This endpoint is for http://localhost:8088/chessfish */
    @GetMapping("/chessfish")
    public String viewGames() {
        return "chessfish"; // Sends to dorito.jsp
    }

    /*  This endpoint is for http://localhost:8088/chessfish2 */
    @GetMapping("/chessfish2")
    public String chessfish2(HttpServletRequest request) {
        chessFishService.setAttributes(request);
        return "chessfish2"; // Sends to dorito2.jsp
    }

    /* This endpoint is used by javascript chessfish ajax chessResponse() */
    @GetMapping("/chessresponse")
    public ResponseEntity<DoritoResponse> chessResponse(String color, String firstName, String lastName, String fideId, String year, boolean titledTuesday) {
        try {
            DoritoResponse doritoResponse = chessFishService.getChessResponse(color, firstName, lastName, fideId, year, titledTuesday);
            return ResponseEntity.ok(doritoResponse);
        } catch (Exception e) {
            log.error("ERR chessResponse! zdt;col;fn;ln;tt:" + ZonedDateTime.now() + ";" + color + ";" + firstName + ";" + lastName + ";" + year + ";" + titledTuesday + ";");
            throw new RuntimeException("ajaxGames Error:" + e.getMessage());
        }
    }

    @GetMapping("/counterTotalGamesInDb")
    public ResponseEntity<String> counterTotalGamesInDb() {
        return ResponseEntity.ok(chessFishService.counterTotalGamesInDb());
    }
}
