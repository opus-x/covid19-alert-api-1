package com.immotef.corona.admin_user.repository;

import com.immotef.corona.company.context.CompanyAwareRepository;
import com.immotef.corona.admin_user.UserEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AdminUserRepository extends CompanyAwareRepository<UserEntity, Long> {
    UserEntity findByToken(String token);

    Set<UserEntity> findByFirstNameLike(String search);

    Set<UserEntity> findByLastNameLike(String search);

    Set<UserEntity> findByEmailLike(String search);

    Optional<UserEntity> findByEmail(String search);

    default UserEntity findByEmailSafe(String username)
    {
        return findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("No admin_user found with email " + username));
    }



}
