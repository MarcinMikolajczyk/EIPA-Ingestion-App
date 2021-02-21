package com.tomtom.ingestion.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter
@ToString
public class Status {
    @JsonProperty("availability")
    private Integer availability;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("ts")
    private Date lastTimeUpdate;
}
