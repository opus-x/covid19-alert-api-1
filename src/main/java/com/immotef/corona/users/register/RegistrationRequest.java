package com.immotef.corona.users.register;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationRequest {
    public String deviceUUID;
    public String lat;
    public String lon;
    public String firebaseToken;
    public String existingUserId;

}
