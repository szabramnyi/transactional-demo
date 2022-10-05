package com.example.transactionaldemo.service;

import com.example.transactionaldemo.entity.Person;
import com.example.transactionaldemo.repository.PersonRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Transactional(timeout = 5)
    public Person saveAndGetPreviousOne(Person person) {
        Long id = repository.save(person).getId();
        return getPerson(id - 1);
    }

    @SneakyThrows
    public Person getPerson(Long id) {
        Thread.sleep(6000);
        return repository.getReferenceById(id);
    }
}
