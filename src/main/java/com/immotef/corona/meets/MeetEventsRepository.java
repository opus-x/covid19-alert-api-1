package com.immotef.corona.meets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MeetEventsRepository extends JpaRepository<MeetEvent, Long> {
    List<MeetEvent> findByUserIdAndEndTimeAfter(long userId, LocalDateTime endTime);
}
