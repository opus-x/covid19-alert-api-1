package com.immotef.corona.admin_user.dto;

import com.immotef.corona.admin_user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserDTO {

    private String email;

    private String password;

    private Role role;

    private String firstName;

    private String lastName;

    private String iconUrl;

}
