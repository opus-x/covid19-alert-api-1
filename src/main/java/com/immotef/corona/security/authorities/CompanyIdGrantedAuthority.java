package com.immotef.corona.security.authorities;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@ToString
@EqualsAndHashCode
public final class CompanyIdGrantedAuthority implements GrantedAuthority {

    private Long companyId;

    public CompanyIdGrantedAuthority(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String getAuthority() {
        return companyId.toString();
    }
}
