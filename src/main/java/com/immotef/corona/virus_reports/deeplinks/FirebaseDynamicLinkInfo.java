package com.immotef.corona.virus_reports.deeplinks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FirebaseDynamicLinkInfo {

    private DynamicLinkInfo dynamicLinkInfo;

    public FirebaseDynamicLinkInfo(String link) {
        this.dynamicLinkInfo = new DynamicLinkInfo(link);
    }
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class DynamicLinkInfo {//TODO move hardcoded strings to config file
    public DynamicLinkInfo(String link) {
        this.link = "https://www.covid19-alert.eu/report?infectionValidationKey=" + link;
        this.domainUriPrefix = "https://alertcoronavirus.page.link";
        this.androidInfo = new AndroidInfo("com.immotef.coronavirusblockade");
        this.iosInfo = new IosInfo("com.immotef.coronavirusblockade");
    }

    private String domainUriPrefix;
    private String link;
    private AndroidInfo androidInfo;
    private IosInfo iosInfo;

}

@Getter
@Setter
@NoArgsConstructor
class AndroidInfo {
    public AndroidInfo(String androidPackageName) {
        this.androidPackageName = androidPackageName;
    }

    private String androidPackageName;
}

@Getter
@Setter
@NoArgsConstructor
class IosInfo {
    public IosInfo(String iosBundleId) {
        this.iosBundleId = iosBundleId;
    }

    private String iosBundleId;
}

/**
 "dynamicLinkInfo": {
 "domainUriPrefix": "https://alertcoronavirus.page.link",
 "link": "https://www.covid19-alert.eu/report?infectionValidationKey=12345_from_dima",
 "androidInfo": {
 "androidPackageName": "com.immotef.coronavirusblockade"
 },
 "iosInfo": {
 "iosBundleId": "com.vidicom.CoronaFight"
 }
 }
 */