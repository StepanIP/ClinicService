package com.example.clinicservice.service;

import com.example.clinicservice.ClinicServiceApplication;
import com.example.clinicservice.dto.request.SignUpRequest;
import com.example.clinicservice.dto.request.SigninRequest;
import com.example.clinicservice.dto.response.JwtAuthenticationResponse;
import com.example.clinicservice.model.Account;
import com.example.clinicservice.model.Patient;
import com.example.clinicservice.model.Person;
import com.example.clinicservice.repository.AccountRepository;
import com.example.clinicservice.repository.PatientRepository;
import com.example.clinicservice.service.security.JwtService;
import com.example.clinicservice.service.security.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes= ClinicServiceApplication.class)
public class AuthenticationServiceImplTest {

    @Mock
    private AccountRepository userRepository;

    @Mock
    private AccountService accountService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Test
    public void testSignup() {
        SignUpRequest signUpRequest = new SignUpRequest("John", "Doe", "father", "johndoe@example.com", "password");

        Account savedUser = new Account();
        when(userRepository.save(any(Account.class))).thenReturn(savedUser);
        when(patientRepository.save(any(Patient.class))).thenReturn(new Patient());

        when(jwtService.generateToken(any(Account.class))).thenReturn("generated-token");

        JwtAuthenticationResponse response = authenticationService.signup(signUpRequest);

        verify(userRepository, times(1)).save(any(Account.class));
        verify(jwtService, times(1)).generateToken(any(Account.class));
    }

    @Test
    public void testSignin() {
        SigninRequest signinRequest = new SigninRequest("johndoe@example.com", "password");

        Account user =Account.builder()
                .email("johndoe@example.com")
                .password("encoded-password")
                .build();

        when(userRepository.findByEmail("johndoe@example.com")).thenReturn(user);
        when(accountService.getPerson(any(Account.class))).thenReturn(new Patient());

        authenticationService.signin(signinRequest);

        verify(authenticationManager, times(1))
                .authenticate(new UsernamePasswordAuthenticationToken("johndoe@example.com", "password"));
        verify(jwtService, times(1)).generateToken(any(UserDetails.class));
    }
}
