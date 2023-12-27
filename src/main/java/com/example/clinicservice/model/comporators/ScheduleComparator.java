package com.example.clinicservice.model.comporators;

import com.example.clinicservice.model.Schedule;

import java.util.Comparator;

public class ScheduleComparator implements Comparator<Schedule> {
    @Override
    public int compare(Schedule schedule1, Schedule schedule2) {
        return schedule1.getAppointmentTime().compareTo(schedule2.getAppointmentTime());
    }
}
