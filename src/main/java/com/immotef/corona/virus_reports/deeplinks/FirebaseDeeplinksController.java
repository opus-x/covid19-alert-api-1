package com.immotef.corona.virus_reports.deeplinks;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirebaseDeeplinksController {
    FirebaseAPIClient firebaseAPIClient;
    FirebaseDeeplinksService firebaseDeeplinksService;

    public FirebaseDeeplinksController(FirebaseDeeplinksService firebaseDeeplinksService) {
        this.firebaseDeeplinksService = firebaseDeeplinksService;
    }

    @RequestMapping("/deeplink")
    public FirebaseDynamicLink generateFirebaseDynamicLink(@RequestBody FirebaseDynamicLinkCreationRequestParam request) { 
        FirebaseDynamicLink result = this.firebaseDeeplinksService.prepareFirebaseDynamicLinkWithDate(request.testedAt);
        return result;
    }
}

@Getter
@Setter
@NoArgsConstructor
class FirebaseDynamicLinkCreationRequestParam {
    public String testedAt;
}