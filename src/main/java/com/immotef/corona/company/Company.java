package com.immotef.corona.company;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "companies")
@SequenceGenerator(name = "COMPANIES_SEQ", sequenceName = "companies_id_sequence", allocationSize = 1)
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPANIES_SEQ")
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "vat_number")
    private String vatNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @Column(name = "blocked")
    private boolean blocked;

//    @OneToMany(mappedBy = "company", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true, fetch = FetchType.LAZY)
//    private Set<Invoice> invoices;

}
