package com.immotef.corona.meets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MeetEventsService {
    private final MeetEventsRepository repository;

    @Autowired
    public MeetEventsService(MeetEventsRepository repository) {
        this.repository = repository;
    }

    public void meetMany(CreateMeetEventsRequest request, long userId) {
        String ip = provideRequestIp(request);
        repository.saveAll(
                request.events.stream()
                        .map(createEventRequest ->
                                MeetEvent.builder()
                                        .userId(userId)
                                        .seenUserId(createEventRequest.getSeenUserId())
                                        .startTime(createEventRequest.start)
                                        .endTime(createEventRequest.end)
                                        .closeDistance(createEventRequest.close_distance)
                                        .closeDistanceLastingTime(createEventRequest.close_distance_lasting_time)
                                        .build()

                        )
                        .collect(Collectors.toList())
        );

    }

    private String provideRequestIp(CreateMeetEventsRequest request) {
        return "";
    }

    private double parceCoordinate(String lat) {
        try {
            return Double.parseDouble(lat);
        } catch (Exception e)
        {
            return 0;
        }
    }
}
