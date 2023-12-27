package com.example.clinicservice.service;


import com.example.clinicservice.model.Appointment;
import com.example.clinicservice.model.Person;

import java.util.List;

public interface AppointmentService {
    Appointment create(Appointment appointment);
    Appointment readById(Long appointmentId);
    Appointment update(Appointment appointment);
    void delete(Appointment appointment);
    List<Appointment> getAll();
    List<Appointment> getAppointmentsForPerson(Person patient);
}
