package com.tomtom.ingestion.services;

import com.tomtom.ingestion.clients.ExportDataWebClient;
import com.tomtom.ingestion.dto.Point;
import com.tomtom.ingestion.dto.events.Event;
import com.tomtom.ingestion.dto.events.EventData;
import com.tomtom.ingestion.dto.events.Status;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PointsService {

    private Date lastChecking;
    private final ExportDataWebClient exportDataWebClient;

    public PointsService(ExportDataWebClient exportDataWebClient) {
        this.exportDataWebClient = exportDataWebClient;
        lastChecking = new Date();
    }

    public List<Point> getUpdatedPoints(){
        List<Point> updatedPoints = exportDataWebClient.getPoints()
                .stream()
                .filter(point ->
                    (point.getStatus() != null && point.getStatus().getLastTimeUpdate().after(lastChecking))
                ).collect(Collectors.toList());
        lastChecking = new Date();
        return updatedPoints;
    }

    public List<Event> mapPointsToEvents(List<Point> points){
        return points.stream()
                .map(point -> {
                    Event event = new Event();
                    EventData metaData = new EventData();
                    event.setId(UUID.randomUUID().toString());
                    event.setTimestamp(new Date());
                    metaData.setDate(point.getStatus().getLastTimeUpdate());
                    metaData.setPointId(point.getCode());
                    switch (point.getStatus().getStatus()){
                        case 0: metaData.setStatus(Status.OCCUPIED); break;
                        case 1: metaData.setStatus(Status.AVAILABLE); break;
                        default: metaData.setStatus(Status.UNKNOWN);
                    }
                    event.setData(metaData);
                    System.out.println(event.toString());
                    return event;
                }).collect(Collectors.toList());
    }
}
