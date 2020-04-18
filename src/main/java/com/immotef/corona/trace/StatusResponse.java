package com.immotef.corona.trace;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse {
    public List<TraceItem>items;
    public List<String>metInfectedIds;
    public int riskLevel;
    public int numberOfInfectedMet;
    public boolean reportedSelfInfection;
    public boolean reportedRecovered;

}

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class MeetItem {
    public String majorMinor;
    public boolean isInfected;
}