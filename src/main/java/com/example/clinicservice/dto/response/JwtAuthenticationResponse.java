package com.example.clinicservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class JwtAuthenticationResponse {
    private String token;
    private String position;

    public JwtAuthenticationResponse(String token, String position) {
        this.token = token;
        this.position = position;
    }
}