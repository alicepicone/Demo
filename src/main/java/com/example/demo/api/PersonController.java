package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/person")
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
    public List<Person> allPeople()
    {
        return personService.getAllPeople();
    }

    @GetMapping("/professionByPerson")
    public String professionByPerson(@RequestParam("name") String name)
    {
        return personService.professionByPersonName(name);
    }

    @GetMapping("/namesByChar")
    public ResponseEntity<String> namesByChar(@RequestParam("char") String character) {

        return personService.namesByChar(character);
    }
}

