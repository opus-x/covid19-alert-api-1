package com.immotef.corona.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "users")
@SequenceGenerator(
        name = "USERS_SEQ",
        sequenceName = "users_id_sequence",
        allocationSize = 1
)
public class User {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
    private long id;

    @NotNull
    private long major;

    @NotNull
    private long minor;

    @Column(name = "virus_report_id")
    private Long reportId;

    @Column(name = "token")
    private String token;

    @Column(name = "firebase_token")
    private String firebaseToken;

    @Column(name = "date_time_recovered")
    private LocalDateTime dateTimeRecovered;

    public String getExternalId() {
        return major + ":" + minor;
    }
    
}
