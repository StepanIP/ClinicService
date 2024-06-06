package com.example.clinicservice.db;

import com.example.clinicservice.model.Account;
import com.example.clinicservice.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataBaseConnectionTests {

    @Autowired
    AccountRepository accountRepository;


    @Test
    @Transactional
    void testCreateUser(){
        Account user = new Account();
        user.setEmail("nyGmail@gmail.com");
        user.setPassword("somePASS12&*");
        int beforeSize = accountRepository.findAll().size();

        accountRepository.save(user);
        Account actual = accountRepository.findByEmail(user.getUsername());

        assertEquals(user.getUsername(), actual.getUsername());
        assertEquals(user.getPassword(), actual.getPassword());
        assertNotEquals(beforeSize, accountRepository.findAll().size());
    }
}
