package com.tomtom.ingestion.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Point {
    @JsonProperty("code")
    private String code;
    @JsonProperty("prices")
    private List<Price> prices = new ArrayList<>();
    @JsonProperty("status")
    private Status status;
}
