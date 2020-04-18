package com.immotef.corona.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private long  id;

    private String firstName;

    private String lastName;

    private String birthDay;

    private String majorMinor;

    private String qrCodeURL;

    public static UserDTO withUser(User user) {
        return UserDTO
                .builder()
                .id(user.getId())
                .majorMinor(user.getMajor()+":" +user.getMinor())
                .qrCodeURL("https://s3.eu-west-2.amazonaws.com/coredeel-bucket/checkerqr_1572942167209_1033353785.png")// TODO add qrcode configuration
                .build();
    }
}
