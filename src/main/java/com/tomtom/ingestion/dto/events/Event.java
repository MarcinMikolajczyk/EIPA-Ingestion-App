package com.tomtom.ingestion.dto.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter
@ToString
public class Event {
    @JsonProperty("id")
    private String id;
    @JsonProperty("ts")
    private Date timestamp;
    @JsonProperty("data")
    private EventData data;
}
