package com.immotef.corona.meets;

import com.immotef.corona.users.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "meet_events")
@SequenceGenerator(
        name = "MEET_EVENTS_SEQ",
        sequenceName = "meet_events_id_sequence",
        allocationSize = 1
)
public class MeetEvent {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEET_EVENTS_SEQ")
    private long id;

    @Column(name = "user_id")
    private long userId;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false, nullable = false)
    private User user;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.LAZY)
    @JoinColumn(name = "seen_user_id", insertable = false, updatable = false, nullable = false)
    private User seenUser;

    @Column(name = "seen_user_id")
    private long seenUserId;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "close_distance")
    private Integer closeDistance;

    @Column(name = "close_distance_lasting_time")
    private Integer closeDistanceLastingTime;

    public long getDaysPassed() {
        LocalDateTime tempDateTime = LocalDateTime.from( endTime );
        return tempDateTime.until( LocalDateTime.now(), ChronoUnit.DAYS );
    }
    @PostLoad
    public void fixNullValues() {
        if(closeDistance == null) {
            closeDistance = 10;
        }

        if(closeDistanceLastingTime == null) {
            closeDistanceLastingTime = 0;
        }
    }
}

