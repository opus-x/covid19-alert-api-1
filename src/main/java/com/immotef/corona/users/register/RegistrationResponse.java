package com.immotef.corona.users.register;

import com.immotef.corona.trace.StatusResponse;
import com.immotef.corona.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {
    public long major;
    public long minor;
    public String authToken;
    public StatusResponse statusResponse;


    public static RegistrationResponse fromUser(User user) {
        return new RegistrationResponse(user.getMajor(), user.getMinor());
    }

    public RegistrationResponse(long major, long minor) {
        this.major = major;
        this.minor = minor;

    }

    public long getIdentifier() {
        return major;
    }
}
