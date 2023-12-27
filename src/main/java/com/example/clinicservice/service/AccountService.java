package com.example.clinicservice.service;

import com.example.clinicservice.model.Account;
import com.example.clinicservice.model.Person;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService {
    Account create(Account account);
    Account readById(Long accountId);
    Account update(Account account);
    void delete(Account account);
    List<Account> getAll();
    UserDetailsService userDetailsService();
    public Person getPerson(Account account);
    public Account loadAccountByEmail(String email);
}
