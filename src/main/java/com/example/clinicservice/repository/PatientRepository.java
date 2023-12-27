package com.example.clinicservice.repository;

import com.example.clinicservice.model.Account;
import com.example.clinicservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByAccount(Account account);
}
