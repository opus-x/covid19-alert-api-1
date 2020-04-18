package com.immotef.corona.admin_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class EncryptPasswordHandler {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public EncryptPasswordHandler(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @HandleBeforeCreate
    @HandleBeforeSave
    public void encryptPassword(UserEntity userEntity) {
//        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword())); TODO encription
        userEntity.setPassword(userEntity.getPassword());
    }
}
