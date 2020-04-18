package com.immotef.corona.virus_reports.deeplinks;

import com.immotef.corona.users.UsersRepository;
import com.immotef.corona.virus_reports.VirusReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VirusReportingCodeService {

    private final VirusReportingCodesRepository virusReportingCodesRepository;

    @Autowired
    public VirusReportingCodeService(VirusReportingCodesRepository virusReportingCodesRepository) {
        this.virusReportingCodesRepository = virusReportingCodesRepository;
    }

    public VirusReportingCode generateCodeForDate(String date) {
        String validationKey = LocalDateTime.now().toString();

        LocalDateTime timeTested = LocalDateTime.parse(date);
        VirusReportingCode code = virusReportingCodesRepository.save(
                VirusReportingCode.builder()
                        .dateTimeCreated(LocalDateTime.now())
                        .dateTimeTested(timeTested)
                        .code(validationKey)
                        .build()
        );

        return code;
    }
}
