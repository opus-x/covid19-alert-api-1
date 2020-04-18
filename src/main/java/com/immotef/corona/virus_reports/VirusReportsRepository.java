package com.immotef.corona.virus_reports;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirusReportsRepository extends JpaRepository<VirusReport, Long> {
}
