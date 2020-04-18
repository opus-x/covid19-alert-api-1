package com.immotef.corona.trace;

import com.immotef.corona.id.Identifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TracingController {

    private final TracingService service;

    @Autowired
    public TracingController(TracingService service) {
        this.service = service;
    }

    @GetMapping("/trace")
    public TraceResponse traceMe(@RequestHeader(Identifier.ID_HEADER_NAME) String userIdentifier) {
        return service.traceForIdentifier(Identifier.getId(userIdentifier));
    }

    @GetMapping("/status")
    public StatusResponse status(@RequestHeader(Identifier.ID_HEADER_NAME) String userIdentifier) {
        return service.statusForIdentifier(Identifier.getId(userIdentifier));
    }

    @GetMapping("/status_only_ids")
    public StatusResponse statusOnlyIds(@RequestHeader(Identifier.ID_HEADER_NAME) String userIdentifier) {
        return service.statusWithoutMeetingUploadForIdentifier(Identifier.getId(userIdentifier));
    }

}
