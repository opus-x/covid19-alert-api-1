package com.immotef.corona.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    @Query(value = "select u.* from users u  join virus_reports r on u.virus_report_id=r.id where r.date_time_created >= timestamp '2020-03-26 00:00'", nativeQuery = true)
//    @Query(value = "select u.* from users u  join virus_reports r on u.virus_report_id=r.id where r.date_time_created >= timestamp :lastCheckTime", nativeQuery = true)
    List<User> findInfectedUsersReportedAfter( @Param("lastCheckTime") LocalDateTime lastCheckTime);
}
