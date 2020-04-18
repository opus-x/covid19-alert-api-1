package com.immotef.corona.virus_reports;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReportInfectionRequest {
    public LocalDateTime testedAt;
    public String infectionValidationKey;
}
