package com.immotef.corona.users.register;

import com.immotef.corona.id.Identifier;
import com.immotef.corona.trace.StatusResponse;
import com.immotef.corona.trace.TracingService;
import com.immotef.corona.users.configuration.VersionUpdateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    private final RegistrationService service;

    private final TracingService tracingService;


    @Autowired
    public RegistrationController(RegistrationService service, TracingService tracingService) {
        this.service = service;
        this.tracingService = tracingService;
    }

    @PostMapping("/register")
    public RegistrationResponse register(@RequestBody RegistrationRequest request) {
        RegistrationResponse registrationResponse = service.register(request);
        registrationResponse.statusResponse = tracingService.statusForIdentifier(registrationResponse.getIdentifier());
        return registrationResponse;
    }

    @PostMapping("/update_firebase_token")
    public StatusResponse firebaseToken(@RequestHeader(Identifier.ID_HEADER_NAME) String userIdentifier, @RequestBody FirebaseTokenUpdateRequest request) throws Exception {
        service.updateFirebaseToken(request, Identifier.getId(userIdentifier));
        return tracingService.statusForIdentifier(Identifier.getId(userIdentifier));
    }

    @PostMapping("/report_recovered")
    public StatusResponse reportRecovered(@RequestHeader(Identifier.ID_HEADER_NAME) String userIdentifier) throws Exception {
        service.reportRecovered(Identifier.getId(userIdentifier));
        return tracingService.statusForIdentifier(Identifier.getId(userIdentifier));
    }

    @GetMapping("/current_version")
    public VersionUpdateInfo provideVersionUpdateInfo() throws Exception {
        return new VersionUpdateInfo();
    }

    @PostMapping("/login_existing_user_with_id")
    public RegistrationResponse loginExistingUserWithId(@RequestBody RegistrationRequest request) throws Exception {
        RegistrationResponse registrationResponse = service.loginExistingUserWithId(request);
        registrationResponse.statusResponse = tracingService.statusForIdentifier(registrationResponse.getIdentifier());
        return registrationResponse;
    }
}
