package com.immotef.corona.virus_reports;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "virus_reports")
@SequenceGenerator(
        name = "VIRUS_REPORTS_SEQ",
        sequenceName = "virus_reports_id_sequence",
        allocationSize = 1
)
public class VirusReport {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VIRUS_REPORTS_SEQ")
    private long id;

    @Builder.Default
    @Column(name = "date_time_created")
    private LocalDateTime dateTimeCreated = LocalDateTime.now();

    @Column(name = "date_time_tested")
    private LocalDateTime dateTimeTested;
}



