package com.immotef.corona.users;

import com.immotef.corona.id.Identifier;
import com.immotef.corona.trace.StatusResponse;
import com.immotef.corona.trace.TracingService;
import com.immotef.corona.users.configuration.VersionUpdateInfo;
import com.immotef.corona.users.register.FirebaseTokenUpdateRequest;
import com.immotef.corona.users.register.RegistrationRequest;
import com.immotef.corona.users.register.RegistrationResponse;
import com.immotef.corona.users.register.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/update_user")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }


    @GetMapping("/users")
    public PagedUsersResult userList(Optional<String> page, @RequestParam Optional<String> filter) throws Exception {
        return userService.userList(page, filter);
    }
}
