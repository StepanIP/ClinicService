package com.example.clinicservice.service.security;


import com.example.clinicservice.dto.request.SignUpRequest;
import com.example.clinicservice.dto.request.SigninRequest;
import com.example.clinicservice.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}