package com.immotef.corona.users.configuration;

import org.springframework.stereotype.Service;

@Service
public class ConfigurationVersionService {
    public VersionUpdateInfo getConfigurationForDate(String last_updated) {
        return new VersionUpdateInfo();
    }
}
