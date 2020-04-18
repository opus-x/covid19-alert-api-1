package com.immotef.corona.security.authorities;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@ToString
@EqualsAndHashCode
public class UserIdGrantedAuthority implements GrantedAuthority {
    private Long userId;

    public UserIdGrantedAuthority(Long userId) {
        this.userId = userId;
    }

    @Override
    public String getAuthority() {
        return userId.toString();
    }
}