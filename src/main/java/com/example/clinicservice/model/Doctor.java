package com.example.clinicservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctors")
public class Doctor extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String doctorName;
    private String surname;
    @Column(name = "fathers_name ")
    private String fathersName;
    private int age;
    private String specialization;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
