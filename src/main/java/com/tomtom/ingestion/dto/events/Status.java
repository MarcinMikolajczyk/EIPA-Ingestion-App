package com.tomtom.ingestion.dto.events;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    @JsonProperty("AVAILABLE")
    AVAILABLE,
    @JsonProperty("OCCUPIED")
    OCCUPIED,
    @JsonProperty("UNKNOWN")
    UNKNOWN
}
