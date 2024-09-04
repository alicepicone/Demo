package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonService(PersonDao personDao)
    {
        this.personDao = personDao;
    }

    public Person addPerson(Person person)
    {
        return personDao.save(person);
    }

    public List<Person> getAllPeople()
    {
        return personDao.findAll();
    }

    public Optional<Person> getPersonById(int id)
    {
        return personDao.findById(id);
    }
}
