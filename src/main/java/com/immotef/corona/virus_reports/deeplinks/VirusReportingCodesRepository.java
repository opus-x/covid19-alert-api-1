package com.immotef.corona.virus_reports.deeplinks;

import com.immotef.corona.meets.MeetEvent;
import com.immotef.corona.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VirusReportingCodesRepository extends JpaRepository<VirusReportingCode, Long> {
    VirusReportingCode findByCode(String code);
}
