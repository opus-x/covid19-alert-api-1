package com.immotef.corona.users.configuration;

import com.immotef.corona.id.Identifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigurationVersionController {

    private final ConfigurationVersionService configurationVersionService;

    @Autowired
    public ConfigurationVersionController(ConfigurationVersionService configurationVersionService) {
        this.configurationVersionService = configurationVersionService;
    }

    @GetMapping("/configuration")
    public VersionUpdateInfo getConfiguration(@RequestHeader(Identifier.ID_HEADER_NAME) String userIdentifier, @RequestParam(required = false) String last_updated) {
        return configurationVersionService.getConfigurationForDate(last_updated);
    }
}
