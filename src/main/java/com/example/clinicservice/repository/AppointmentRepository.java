package com.example.clinicservice.repository;

import com.example.clinicservice.model.Appointment;
import com.example.clinicservice.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientOrderByAppointmentTime(Person patient);
    List<Appointment> findByDoctorOrderByAppointmentTime(Person patient);


}
