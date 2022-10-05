package com.example.transactionaldemo.repository;

import com.example.transactionaldemo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
