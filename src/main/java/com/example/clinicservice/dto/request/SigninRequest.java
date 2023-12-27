package com.example.clinicservice.dto.request;

import lombok.*;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SigninRequest {
    private String email;
    private String password;
}