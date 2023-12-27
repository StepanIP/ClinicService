package com.example.clinicservice.service.impl;

import com.example.clinicservice.model.Appointment;
import com.example.clinicservice.model.Doctor;
import com.example.clinicservice.model.Person;
import com.example.clinicservice.repository.AppointmentRepository;
import com.example.clinicservice.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment create(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment readById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).orElse(null);
    }

    @Override
    public Appointment update(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void delete(Appointment appointment) {
        appointmentRepository.delete(appointment);
    }

    @Override
    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getAppointmentsForPerson(Person person) {
        if (person instanceof Doctor doctor) {
            return appointmentRepository.findByDoctorOrderByAppointmentTime(person);
        }
        else {
            return appointmentRepository.findByPatientOrderByAppointmentTime(person);
        }
    }


}
