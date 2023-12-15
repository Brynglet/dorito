package com.example.repository;

import com.example.domain.DoritoGame;
import com.example.utility.FileUtility;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.utility.Common.*;

@Component
public class DoritoRepository {

    List<Integer> easy = List.of(15, 6, 3); //  1,2,3 trianlges
    List<Integer> medium = List.of(20, 8, 6); // 1,2,3 trianlges
    List<Integer> hard = List.of(24, 12, 8); // 1,2,3 trianlges
    public List<DoritoGame> getDoritos() {
        List<DoritoGame> doritoGames = new ArrayList<>();

        return doritoGames;
    }


}
