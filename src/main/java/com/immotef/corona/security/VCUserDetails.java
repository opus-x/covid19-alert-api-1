package com.immotef.corona.security;

import com.immotef.corona.admin_user.Role;
import com.immotef.corona.admin_user.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class VCUserDetails extends User {

    private long companyId;
    private long id;
    private Role role;

    public VCUserDetails(String username, String password,
                         Collection<? extends GrantedAuthority> authorities, long companyId, long id, Role role) {
        super(username, password, authorities);
        this.companyId = companyId;
        this.id = id;
        this.role = role;
    }

    public VCUserDetails(String username, String password, boolean enabled,
                              boolean accountNonExpired, boolean credentialsNonExpired,
                              boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
                              long companyId, long id, Role role) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.companyId = companyId;
        this.id = id;
        this.role = role;
    }

    public VCUserDetails(UserEntity userEntity) {
        super(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
        this.companyId = userEntity.getCompanyId();
        this.id = userEntity.getId();
        this.role = userEntity.getRole();
    }
}
