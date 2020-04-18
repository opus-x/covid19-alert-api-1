package com.immotef.corona.virus_reports;

import com.immotef.corona.id.Identifier;
import com.immotef.corona.trace.StatusResponse;
import com.immotef.corona.trace.TracingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VirusReportsController {

    private final VirusReportService virusReportService;
    private final TracingService service;


    @Autowired
    public VirusReportsController(VirusReportService virusReportService, TracingService service) {
        this.virusReportService = virusReportService;
        this.service = service;
    }

    @PostMapping("/report")
    public StatusResponse reportUser(@RequestHeader(Identifier.ID_HEADER_NAME) String userIdentifier,
                                     @RequestBody ReportInfectionRequest reportInfectionRequest) {
        virusReportService.reportInfection(reportInfectionRequest, Identifier.getId(userIdentifier));
        return service.statusForIdentifier(Identifier.getId(userIdentifier));
    }

    @PostMapping("/report_infected_validation_key")
    public StatusResponse reportWithInfectedValidationKey(@RequestHeader(Identifier.ID_HEADER_NAME) String userIdentifier,
                                                @RequestBody ReportInfectionRequest reportInfectionRequest) throws Exception {
        virusReportService.reportInfectionWithInfectedTestId(reportInfectionRequest, Identifier.getId(userIdentifier));
        return service.statusForIdentifier(Identifier.getId(userIdentifier));
    }

}
