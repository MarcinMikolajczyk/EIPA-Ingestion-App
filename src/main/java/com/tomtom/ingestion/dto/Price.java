package com.tomtom.ingestion.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Getter @Setter
@ToString
public class Price {
    @JsonFormat(shape= JsonFormat.Shape.STRING)
    private BigDecimal price;
    @JsonProperty("ts")
    private Date lastTimeUpdate;
    @JsonProperty("literal")
    private String literal;
    @JsonProperty("unit")
    private Unit unit;
}
