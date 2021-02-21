package com.tomtom.ingestion.dto.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter
@ToString
public class EventData {
    @JsonProperty("pointId")
    private String pointId;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("originalTs")
    private Date date;
}
