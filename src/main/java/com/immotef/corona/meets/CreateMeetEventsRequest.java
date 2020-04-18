package com.immotef.corona.meets;

import com.immotef.corona.id.Identifier;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateMeetEventsRequest {
    public List<CreateMeetEventRequest> events = new ArrayList<>();
}

@Getter
@Setter
@NoArgsConstructor
class CreateMeetEventRequest {
    public LocalDateTime start;
    public LocalDateTime end;
    public String seenUserId;
    public String lat;
    public String lon;
    public int close_distance;
    public int close_distance_lasting_time;


    public long getSeenUserId() {
        return Identifier.getId(seenUserId);
    }
}