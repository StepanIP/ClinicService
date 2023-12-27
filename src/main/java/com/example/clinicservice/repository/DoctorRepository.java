package com.example.clinicservice.repository;

import com.example.clinicservice.model.Account;
import com.example.clinicservice.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByAccount(Account account);
}