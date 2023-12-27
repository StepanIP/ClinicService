package com.example.clinicservice.service.security.impl;

import com.example.clinicservice.dto.request.SignUpRequest;
import com.example.clinicservice.dto.request.SigninRequest;
import com.example.clinicservice.dto.response.JwtAuthenticationResponse;
import com.example.clinicservice.model.Account;
import com.example.clinicservice.model.Doctor;
import com.example.clinicservice.model.Patient;
import com.example.clinicservice.repository.AccountRepository;
import com.example.clinicservice.repository.PatientRepository;
import com.example.clinicservice.service.AccountService;
import com.example.clinicservice.service.security.AuthenticationService;
import com.example.clinicservice.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final PatientRepository patientRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        Account account = Account.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        accountRepository.save(account);

        patientRepository.save(Patient.builder()
                .patientName(request.getName())
                .surname(request.getSurname())
                .fathersName(request.getFathersName())
                .account(account)
                .build()
        );
        var jwt = jwtService.generateToken(account);
        return JwtAuthenticationResponse.builder().token(jwt).position("patient").build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var account = accountRepository.findByEmail(request.getEmail());
        var user = accountService.getPerson(account);
        var jwt = jwtService.generateToken(account);
        return JwtAuthenticationResponse.builder().token(jwt).position(user instanceof Doctor ? "doctor" : "patient").build();
    }
}