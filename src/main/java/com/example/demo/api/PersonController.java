package com.example.demo.api;

import com.example.demo.exception.InvalidInputException;
import com.example.demo.exception.NoResultsFoundException;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/persons")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService)
    {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody Person person)
    {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople()
    {
        return personService.getAllPeople();
    }

    @GetMapping("/names")
    public ResponseEntity<String> getNamesByChar(@RequestParam("char") String character) throws InvalidInputException, NoResultsFoundException {

        return personService.namesByChar(character);
    }
}

