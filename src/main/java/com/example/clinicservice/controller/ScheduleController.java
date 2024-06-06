package com.example.clinicservice.controller;

import com.example.clinicservice.model.Doctor;
import com.example.clinicservice.model.Person;
import com.example.clinicservice.model.Schedule;
import com.example.clinicservice.service.AccountService;
import com.example.clinicservice.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Clinic-name/schedule")
public class ScheduleController {

    final AccountService accountService;
    final ScheduleService scheduleService;

    public ScheduleController(AccountService accountService, ScheduleService scheduleService) {
        this.accountService = accountService;
        this.scheduleService = scheduleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Schedule>> allSchedules(Principal principal) {
        Person person = accountService.getPerson(accountService.loadAccountByEmail(principal.getName()));
        List<Schedule> list = scheduleService.getSchedulesByDoctorOrderByDayOfWeek(person);
            list.sort(Comparator.comparing(Schedule::getAppointmentTime));
            return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/byDay")
    public ResponseEntity<List<Schedule>> schedulesByDay(@RequestParam("day") String day, Principal principal) {
        Doctor person = (Doctor) accountService.getPerson(accountService.loadAccountByEmail(principal.getName()));
        List<Schedule> list = scheduleService.findAllByDoctorAndDayOfWeek(person, day);
        list.sort(Comparator.comparing(Schedule::getAppointmentTime));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
