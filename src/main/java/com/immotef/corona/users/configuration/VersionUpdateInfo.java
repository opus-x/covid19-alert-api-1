package com.immotef.corona.users.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VersionUpdateInfo {
    private String currentVersionIOS = "1.0";
    private String currentVersionAndroid;
    private String iosVersionUpdateMessage = "Please update app from AppDestribution";
    private String androidVersionUpdateMessage;
    private String configPl = "url to file";
    private String configEnglish = "url to file";
}
