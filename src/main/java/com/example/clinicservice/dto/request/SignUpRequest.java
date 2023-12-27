package com.example.clinicservice.dto.request;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class SignUpRequest {
    String name;
    String surname;
    String fathersName;
    String email;
    String password;
}
