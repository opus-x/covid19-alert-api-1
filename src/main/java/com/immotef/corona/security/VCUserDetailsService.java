package com.immotef.corona.security;

import com.immotef.corona.admin_user.UserEntity;
import com.immotef.corona.admin_user.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class VCUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private AdminUserRepository repository;

    @Autowired
    public VCUserDetailsService(AdminUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public VCUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByEmailSafe(username);

        return new VCUserDetails(userEntity.getEmail(), userEntity.getPassword(), Collections.emptyList(),
                userEntity.getCompanyId(), userEntity.getId(), userEntity.getRole());
    }
}
