package com.immotef.corona.admin_user;

import com.immotef.corona.company.CompanyAwareEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin_users")
@SequenceGenerator(name = "USERS_SEQ", sequenceName = "admin_users_id_sequence", allocationSize = 1)
public class UserEntity extends CompanyAwareEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMIN_USERS_SEQ")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    @Column(name = "token")
    private String token;
//
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
//
//    @Column(name = "icon_url")
//    private String iconUrl = "";
}

