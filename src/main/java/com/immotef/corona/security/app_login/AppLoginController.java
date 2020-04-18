package com.immotef.corona.security.app_login;

import com.immotef.corona.security.UserTokenBuilder;
import com.immotef.corona.admin_user.LoginRequestBody;
import com.immotef.corona.admin_user.UserEntity;
import com.immotef.corona.admin_user.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AppLoginController {

    private AdminUserRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AppLoginController(AdminUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login/app")
    public LoginResponse login(@RequestBody LoginRequestBody body) throws UsernameNotFoundException, NotAuthenticatedException {
        UserEntity userAttempting = repository.findByEmailSafe(body.username);

//        if(!passwordEncoder.matches(body.password, userAttempting.getPassword())) { TODO encription
        if(!body.password.equals(userAttempting.getPassword())) {
            throw new NotAuthenticatedException();
        }
        return new LoginResponse(UserTokenBuilder.getLoggedUserToken(userAttempting));
    }

}


