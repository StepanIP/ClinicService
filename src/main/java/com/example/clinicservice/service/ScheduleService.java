package com.example.clinicservice.service;

import com.example.clinicservice.model.Doctor;
import com.example.clinicservice.model.Person;
import com.example.clinicservice.model.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule create(Schedule schedule);
    Schedule readById(Long scheduleId);
    Schedule update(Schedule schedule);
    void delete(Schedule schedule);
    List<Schedule> getAll();
    List<Schedule> getSchedulesByDoctorOrderByDayOfWeek(Person doctor);
    List<Schedule> findAllByDoctorAndDayOfWeek(Doctor doctor, String day);
}
