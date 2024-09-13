package com.example.demo.api;

import com.example.demo.dto.PersonDto;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<PersonDto> getAllPeople()
    {
        return personService.getAllPeople();
    }

    @GetMapping("/{id}")
    public Optional<PersonDto> getPeopleById(@PathVariable("id") int id)
    {
        return personService.getPeopleById(id);
    }

    @GetMapping("/names")
    @SneakyThrows
    public ResponseEntity<String> getNamesByChar(@RequestParam("char") String character) {

        return personService.getNamesByChar(character);
    }
}

