package com.example.clinicservice.repository;

import com.example.clinicservice.model.Person;
import com.example.clinicservice.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByDoctorOrderByDayOfWeek(Person doctor);

    List<Schedule> findAllByDoctorAndDayOfWeek(Person docto, String dayr);
}
