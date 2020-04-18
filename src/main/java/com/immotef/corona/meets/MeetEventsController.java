package com.immotef.corona.meets;

import com.immotef.corona.id.Identifier;
import com.immotef.corona.trace.StatusResponse;
import com.immotef.corona.trace.TracingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeetEventsController {

    private final MeetEventsService meetEventsService;
    private final TracingService tracingService;

    @Autowired
    public MeetEventsController(MeetEventsService meetEventsService, TracingService tracingService) {
        this.meetEventsService = meetEventsService;
        this.tracingService = tracingService;
    }

    @PostMapping("/meet")
    public StatusResponse meetOccurred(
            @RequestHeader(Identifier.ID_HEADER_NAME) String userIdentifier,
            @RequestBody CreateMeetEventsRequest request) {
        meetEventsService.meetMany(request, Identifier.getId(userIdentifier));
        return tracingService.statusForIdentifier(Identifier.getId(userIdentifier));
    }


}
