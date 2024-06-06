package com.example.clinicservice.controller;

import com.example.clinicservice.dto.response.AccountResponse;
import com.example.clinicservice.dto.response.AppointmentResponse;
import com.example.clinicservice.dto.response.PatientResponse;
import com.example.clinicservice.dto.response.PersonResponse;
import com.example.clinicservice.model.Appointment;
import com.example.clinicservice.model.Doctor;
import com.example.clinicservice.model.Patient;
import com.example.clinicservice.model.Person;
import com.example.clinicservice.service.AccountService;
import com.example.clinicservice.service.AppointmentService;
import com.example.clinicservice.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/Clinic-name/account")
public class AccountController {

    private final AccountService accountService;
    private final AppointmentService appointmentService;
    private final PatientService patientService;

    public AccountController(AccountService accountService, AppointmentService appointmentService, PatientService patientService) {
        this.accountService = accountService;
        this.appointmentService = appointmentService;
        this.patientService = patientService;
    }

    @GetMapping(value = "/private-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> getPrivateData(Principal principal) {
        String username = principal.getName();
        String maskedPassword = "*".repeat(accountService.loadAccountByEmail(username).getPassword().length());
        AccountResponse accountResponse = new AccountResponse(username, maskedPassword);

        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }

    @PostMapping("/insurance-check")
    public ResponseEntity<HttpStatus> checkInsurance(
            @RequestParam("insuranceDocument") MultipartFile insuranceDocument,
            Principal principal) {
        Person person = accountService.getPerson(accountService.loadAccountByEmail(principal.getName()));
        if (insuranceDocument.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (isValidInsuranceDocument(insuranceDocument)) {
            if (person instanceof Patient patient) {
                patient.setFreeServicesLeft(10);
                patientService.update(patient);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/insurance-check")
    public ResponseEntity<String> checkInsurance(
            Principal principal) {
        Person person = accountService.getPerson(accountService.loadAccountByEmail(principal.getName()));
        if (person instanceof Patient patient) {
            return new ResponseEntity<>(String.valueOf(patient.getFreeServicesLeft()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    private boolean isValidInsuranceDocument(MultipartFile file) {
        try {
            String contentType = Objects.requireNonNull(file.getContentType());
            return contentType.equals("image/png");
        } catch (NullPointerException e) {
            return false;
        }
    }

    @GetMapping(value = "/public-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponse> getPublicData(Principal principal) {
            Person person = accountService.getPerson(accountService.loadAccountByEmail(principal.getName()));
            List<AppointmentResponse> appointmentResponses = new ArrayList<>();
            List<Appointment> appointments = appointmentService.getAppointmentsForPerson(person);
            for (int i = 0; i < appointments.size(); i++) {
                appointmentResponses.add(new AppointmentResponse(appointments.get(i)));
            }

            PersonResponse personResponse = null;
            if (person instanceof Patient patient) {
                personResponse = new PersonResponse(
                        patient.getPatientName(),
                        patient.getSurname(),
                        patient.getFathersName(),
                        String.valueOf(patient.getAge()),
                        appointmentResponses
                );
            } else if(person instanceof Doctor doctor){
                personResponse = new PersonResponse(
                        doctor.getDoctorName(),
                        doctor.getSurname(),
                        doctor.getFathersName(),
                        String.valueOf(doctor.getAge()),
                        appointmentResponses
                );
            }
            return new ResponseEntity<>(personResponse, HttpStatus.OK);
    }

    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponse>> getPatients(Principal principal) {
        Doctor person = (Doctor) accountService.getPerson(accountService.loadAccountByEmail(principal.getName()));
        List<Appointment> appointments = appointmentService.getAppointmentsForPerson(person);
        List<PatientResponse> patients = new ArrayList<>();

        for (Appointment a : appointments) {
            Patient patient = a.getPatient();
            boolean patientExists = false;

            for (PatientResponse existingPatient : patients) {
                if (existingPatient.getEmail().equals(patient.getAccount().getUsername())) {
                    existingPatient.getAppointments().add(new AppointmentResponse(a));
                    patientExists = true;
                    break;
                }
            }

            if (!patientExists) {
                List<AppointmentResponse> appointmentResponses = new ArrayList<>();
                appointmentResponses.add(new AppointmentResponse(a));

                PatientResponse patientResponse = new PatientResponse(
                        patient.getPatientName(),
                        patient.getSurname(),
                        patient.getFathersName(),
                        String.valueOf(patient.getAge()),
                        String.valueOf(patient.getFreeServicesLeft()),
                        patient.getAccount().getUsername(),
                        appointmentResponses
                );

                patients.add(patientResponse);
            }
        }

        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}
