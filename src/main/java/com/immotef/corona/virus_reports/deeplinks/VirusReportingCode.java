package com.immotef.corona.virus_reports.deeplinks;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "virus_reporting_codes")
@SequenceGenerator(
        name = "VIRUS_REPORTING_CODES_SEQ",
        sequenceName = "virus_reporting_codes_id_sequence",
        allocationSize = 1
)
public class VirusReportingCode {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VIRUS_REPORTING_CODES_SEQ")
    private long id;

    @Builder.Default
    @Column(name = "date_time_used")
    private LocalDateTime dateTimeCreated = LocalDateTime.now();

    @Column(name = "date_time_tested")
    private LocalDateTime dateTimeTested;

    @Column(name = "code")
    private String code;

    @Column(name = "is_used")
    @Builder.Default
    private Boolean isUsed = false;
}



