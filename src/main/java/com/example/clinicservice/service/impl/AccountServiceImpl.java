package com.example.clinicservice.service.impl;

import com.example.clinicservice.model.Account;
import com.example.clinicservice.model.Person;
import com.example.clinicservice.repository.AccountRepository;
import com.example.clinicservice.repository.DoctorRepository;
import com.example.clinicservice.repository.PatientRepository;
import com.example.clinicservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    private final DoctorRepository doctorRepository;

    private final PatientRepository patientRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.accountRepository = accountRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account readById(Long accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    @Override
    public Account update(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void delete(Account account) {
        accountRepository.delete(account);
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) {
                return accountRepository.findByEmail(email);
            }
        };
    }

    @Override
    public Account loadAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Person getPerson(Account account) {
        if(doctorRepository.findByAccount(account)!=null){
            return  doctorRepository.findByAccount(account);
        }
        else{
            return  patientRepository.findByAccount(account);
        }
    }
}
