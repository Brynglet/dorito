package com.example.domain;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Data
public class DoritoGame {
    private int length;
    private List<Box> boxes;
    List<String> solutions;
}
