package com.immotef.corona.virus_reports;

import com.immotef.corona.users.User;
import com.immotef.corona.users.UsersRepository;
import com.immotef.corona.virus_reports.deeplinks.VirusReportingCode;
import com.immotef.corona.virus_reports.deeplinks.VirusReportingCodesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VirusReportService {

    private final VirusReportsRepository reportsRepository;
    private final UsersRepository usersRepository;
    private final VirusReportingCodesRepository virusReportingCodesRepository;

    @Autowired
    public VirusReportService(VirusReportsRepository reportsRepository,
                              UsersRepository usersRepository,
                              VirusReportingCodesRepository virusReportingCodesRepository) {
        this.reportsRepository = reportsRepository;
        this.usersRepository = usersRepository;
        this.virusReportingCodesRepository = virusReportingCodesRepository;
    }

    public void reportInfection(ReportInfectionRequest request, long userId) {
        VirusReport report = reportsRepository.save(
                VirusReport.builder()
                .dateTimeCreated(request.testedAt)
                .dateTimeTested(provideTestedDate(request))
                .build()
        );

        User user = usersRepository.findById(userId)
                .orElseThrow(IllegalArgumentException::new);

        user.setReportId(report.getId()); //TODO handle report types

        usersRepository.save(user);

    }

    private LocalDateTime provideTestedDate(ReportInfectionRequest request) {
        return LocalDateTime.now(); //TODO handle code date
    }

    public void reportInfectionWithInfectedTestId(ReportInfectionRequest reportInfectionRequest, long userId) throws Exception {
        if (codeWasNotYetUsed(reportInfectionRequest)) {
            reportInfectionWithCode(reportInfectionRequest, userId);
        } else {
            throw new Exception("Code was already used");
        }
    }

    private void reportInfectionWithCode(ReportInfectionRequest reportInfectionRequest, long userId) {
        virusReportingCodesRepository.save(
                VirusReportingCode.builder()
                        .dateTimeCreated(LocalDateTime.now())
                        .dateTimeTested(LocalDateTime.now()) //TODO load from existing code data
                        .code(reportInfectionRequest.infectionValidationKey)
                        .build()
        );

        reportInfection(reportInfectionRequest, userId);
    }

    private boolean codeWasNotYetUsed(ReportInfectionRequest reportInfectionRequest) {
        VirusReportingCode virusReportingCode = virusReportingCodesRepository.findByCode(reportInfectionRequest.infectionValidationKey);
        return virusReportingCode == null;
    }
}
