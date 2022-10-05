package com.example.transactionaldemo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.transactionaldemo.entity.Person;
import com.example.transactionaldemo.repository.PersonRepository;
import org.hibernate.TransactionException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;

@SpringBootTest
class TransactionalExpirationDemo {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonService service;

    @Test
    void timeOutExpirationDemo() {
        Person person = Person.builder()
                .id(1L)
                .fistName("Sibma")
                .lastName("LionKinkg")
                .email("zoo@gmail.com")
                .build();

        repository.save(person);

        person.setId(2L);
        JpaSystemException thrown = assertThrows(JpaSystemException.class,
                () -> service.saveAndGetPreviousOne(person));
        assertEquals(TransactionException.class, thrown.getCause().getClass());
        assertEquals("transaction timeout expired", thrown.getCause().getMessage());
    }
}