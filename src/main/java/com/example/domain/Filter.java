package com.example.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Filter {
    private String color;
    private String firstName;
    private String lastName;
    private String fideId;
    private String year;
    private Boolean titledTuesday;
}
