package com.immotef.corona.admin_user.web;

import com.immotef.corona.exception.ElementNotFoundException;
import com.immotef.corona.admin_user.UserEntity;
import com.immotef.corona.admin_user.action.AdminUserService;
import com.immotef.corona.admin_user.dto.UserActivationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class AdminUserController {

    private AdminUserService adminUserService;

    @Autowired
    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @PostMapping("/custom/admin_users")
    public void addUser(
            @RequestParam(value = "email") String email) {
        adminUserService.addUser(email);
    }

    @PostMapping("custom/admin_users/activation")
    public void addPassword(@RequestBody UserActivationDTO dto) {
        adminUserService.addPassword(dto.getToken(), dto.getPassword());
    }

    @PostMapping("/custom/admin_users/{userId}/icon")
    public void addIconUrl(
            @PathVariable long userId,
            @RequestParam(value = "file") MultipartFile file) throws IOException, ElementNotFoundException {
        adminUserService.addIconUrl(userId, file);
    }

    @GetMapping("/custom/admin_users/users/custom_search")
    public List<UserEntity> getUsers(
            @RequestParam(value = "search") String search) {
        return adminUserService.getUsers(search);
    }

}
