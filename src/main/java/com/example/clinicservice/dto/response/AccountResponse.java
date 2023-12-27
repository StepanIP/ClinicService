package com.example.clinicservice.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountResponse {
    String email;
    String password;

    public AccountResponse(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
