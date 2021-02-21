package com.tomtom.ingestion.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomtom.ingestion.dto.Point;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExportDataWebClient {

    @Value("${export-data}")
    private String EXPORT_DATA_URL;
    @Value("${export-data-token}")
    private String EXPORT_DATA_TOKEN;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public ExportDataWebClient(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder
                .codecs(c -> c
                .defaultCodecs()
                .maxInMemorySize(16 * 1024 * 1024))
                .build();
        objectMapper = new ObjectMapper();
    }

    @SneakyThrows
    public List<Point> getPoints(){
        return webClient.get()
                .uri(String.format("%s/{token}", EXPORT_DATA_URL), EXPORT_DATA_TOKEN)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(s -> s.path("data"))
                .map(n -> {
                    try { return objectMapper.readValue(n.toPrettyString(), new TypeReference<List<Point>>() {}); }
                    catch (JsonProcessingException e) { e.printStackTrace(); }
                    return new ArrayList<Point>();
                }).block();
    }



}
