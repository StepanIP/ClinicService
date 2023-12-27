package com.example.clinicservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "patients")
public class Patient extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patientName;
    private String surname;

    @Column(name = "fathers_name ")
    private String fathersName;

    private String insuranceId;
    private int age;
    private int freeServicesLeft;
    
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
