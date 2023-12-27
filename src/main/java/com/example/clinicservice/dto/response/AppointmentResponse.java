package com.example.clinicservice.dto.response;

import com.example.clinicservice.model.Appointment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@ToString
@Getter
public class AppointmentResponse {
    String appointmentName;
    String appointmentTime;
    String office;

    public AppointmentResponse(Appointment appointment) {
        appointmentName=appointment.getProcedure().getProcedureName();
        appointmentTime=appointment.getAppointmentTime().toString();
        office=String.valueOf(appointment.getProcedure().getOffice());
    }
}
