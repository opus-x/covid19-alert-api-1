package com.immotef.corona.trace;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TraceResponse {
    public List<TraceItem> items = new ArrayList<>();
    public int riskLevel = 0;
}

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class TraceItem {
    public String seenUserId;
    public boolean isInfected = false;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-ddTHH:mm:ssZ")
    public LocalDateTime lastSeen;
    public long duration;
    public long daysPassed;
    public int distance;
}