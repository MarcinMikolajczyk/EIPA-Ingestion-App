package com.tomtom.ingestion.events;

import com.tomtom.ingestion.dto.events.Events;
import com.tomtom.ingestion.services.PointsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@Configuration
@RestController
@RequestMapping("/api")
public class PointsController {

    @Value("${repeat-rate}")
    private Long REPEAT_RATE;
    private final PointsService pointsService;

    public PointsController(PointsService pointsService) {
        this.pointsService = pointsService;
    }

    @GetMapping(value = "/updated-points", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> products(){
        return Flux.from(integrationFlow()).map(Message::getPayload);
    }

    @Bean
    public Publisher<Message<String>> integrationFlow(){
        return IntegrationFlows.fromSupplier(pointsService::getUpdatedPoints,
                p -> p.poller(spec->spec.fixedRate(REPEAT_RATE)))
                .transform(pointsService::mapPointsToEvents)
                .transform(Events::new)
                .channel(MessageChannels.queue(1)).toReactivePublisher();
    }
}
