package com.immotef.corona.virus_reports.deeplinks;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//TODO move hardcoded strings to config file name = "${feign.name}", url = "${feign.url}"
//@FeignClient(value = "FirebaseAPIClient", url = "https://firebasedynamiclinks.googleapis.com",configuration = FirebaseClientConfiguration.class)
@FeignClient(value = "${firebase.api.client.name}", url = "${firebase.url}",configuration = FirebaseClientConfiguration.class)
public interface FirebaseAPIClient {
    @RequestMapping(method = RequestMethod.POST, value = "/v1/shortLinks?key=${firebase.app_key}", produces = "application/json")
    public FirebaseDynamicLink getFirebaseDynamicLink(@RequestBody FirebaseDynamicLinkInfo requestBody);
}

