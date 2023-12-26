package com.example.controller;

import com.example.domain.DoritoResponse;
import com.example.service.DoritoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.ZonedDateTime;

@Slf4j
@Controller
public class DoritoController {

    private final DoritoService doritoService;

    @Autowired
    public DoritoController(DoritoService doritoService) {
        this.doritoService = doritoService;
    }

    @GetMapping("/dorito/{nrOfBlackBoxes}")
    //public ResponseEntity<DoritoResponse> doritoResponse(String nrOfBlackBoxes) {
        public String doritoResponse(HttpServletRequest request, @PathVariable(value = "nrOfBlackBoxes") String nrOfBlackBoxes) {

        try {

            DoritoResponse doritoResponse = doritoService.getDoritoResponse(Integer.parseInt(nrOfBlackBoxes));

            //return ResponseEntity.ok(doritoResponse);

            String theResp = doritoResponse.getRespString();


            //request.setAttribute("doritoTable", doritoResponse.getRespString());
            request.setAttribute("doritoTables",theResp);

            return "dorito"; // Sends to dorito.jsp
        } catch (Exception e) {
            log.error("ERR " + ZonedDateTime.now());
            throw new RuntimeException("doritoResponse Error:" + e.getMessage());
        }
    }

}
