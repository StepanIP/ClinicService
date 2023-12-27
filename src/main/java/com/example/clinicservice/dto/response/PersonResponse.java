package com.example.clinicservice.dto.response;

import com.example.clinicservice.dto.response.AppointmentResponse;
import lombok.*;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class PersonResponse {

    String name;
    String surname;
    String fathersName;
    String age;
    List<AppointmentResponse> appointments;

    public PersonResponse(String name, String surname, String fathersName, String age, List<AppointmentResponse> appointments) {
        this.name = name;
        this.surname = surname;
        this.fathersName = fathersName;
        this.age = age;
        this.appointments = appointments;
    }
}
