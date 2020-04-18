package com.immotef.corona.virus_reports.deeplinks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseDeeplinksService {

    private final FirebaseAPIClient firebaseAPIClient;

    private final VirusReportingCodeService virusReportingCodeService;

    @Autowired
    public FirebaseDeeplinksService(FirebaseAPIClient firebaseAPIClient, VirusReportingCodeService virusReportingCodeService) {
        this.firebaseAPIClient = firebaseAPIClient;
        this.virusReportingCodeService = virusReportingCodeService;
    }

    public FirebaseDynamicLink prepareFirebaseDynamicLinkWithDate(String testedAt) {

        VirusReportingCode virusReportingCode = virusReportingCodeService.generateCodeForDate(testedAt);
        FirebaseDynamicLinkInfo info = new FirebaseDynamicLinkInfo(virusReportingCode.getCode());
        FirebaseDynamicLink result = this.firebaseAPIClient.getFirebaseDynamicLink(info);
        return result;
    }
}
