package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonDao extends JpaRepository<Person, Integer> {

    Person findByNameAndSurname(String name, String surname);
    List<Person> findByNameStartingWithIgnoreCase(String character);
}
