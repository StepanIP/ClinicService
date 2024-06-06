package com.example.clinicservice.service.impl;

import com.example.clinicservice.model.Doctor;
import com.example.clinicservice.model.Person;
import com.example.clinicservice.model.Schedule;
import com.example.clinicservice.repository.ScheduleRepository;
import com.example.clinicservice.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule create(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule readById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElse(null);
    }

    @Override
    public Schedule update(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public void delete(Schedule schedule) {
        scheduleRepository.delete(schedule);
    }

    @Override
    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> getSchedulesByDoctorOrderByDayOfWeek(Person doctor) {
        return scheduleRepository.findAllByDoctorOrderByDayOfWeek(doctor);
    }

    @Override
    public List<Schedule> findAllByDoctorAndDayOfWeek(Doctor doctor, String day){
        return scheduleRepository.findAllByDoctorAndDayOfWeek(doctor ,day);
    };
}
