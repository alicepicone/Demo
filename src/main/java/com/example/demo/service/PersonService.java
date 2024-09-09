package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public String professionByPersonName(String name)
    {
        Person person = personDao.findByName(name);

        if(person == null)
        {
            return "name doesn't exist!";
        }
        return person.getProfession().getDescription();
    }

    public boolean isValidCharacter(String character) {
        return character != null && !character.isBlank() && character.matches("[a-zA-Z]");
    }

    public String getNamesStartingWith(String character) {
        List<Person> persons = personDao.findByNameStartingWithIgnoreCase(character);

        return persons.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", "));
    }
}
