package com.tomtom.ingestion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Unit {
    @JsonProperty("min")
    MIN,
    @JsonProperty("kWh")
    KWH,
    @JsonProperty("m3")
    M3
}
