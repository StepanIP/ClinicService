package com.example.clinicservice.dto.response;


import com.example.clinicservice.dto.response.AppointmentResponse;
import lombok.*;

import java.util.List;

public class PatientResponse {
    String name;
    String surname;
    String fathersName;
    String age;
    String freeServicesLeft;
    String email;

    @EqualsAndHashCode.Exclude
    List<AppointmentResponse> appointments;

    public PatientResponse(String name, String surname, String fathersName, String age, String freeServicesLeft, String email, List<AppointmentResponse> appointments) {
        this.name = name;
        this.surname = surname;
        this.fathersName = fathersName;
        this.age = age;
        this.freeServicesLeft = freeServicesLeft;
        this.email = email;
        this.appointments = appointments;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFathersName() {
        return fathersName;
    }

    public String getAge() {
        return age;
    }

    public String getFreeServicesLeft() {
        return freeServicesLeft;
    }

    public String getEmail() {
        return email;
    }

    public List<AppointmentResponse> getAppointments() {
        return appointments;
    }
}
