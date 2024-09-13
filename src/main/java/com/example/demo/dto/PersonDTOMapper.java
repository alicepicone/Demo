package com.example.demo.dto;

import com.example.demo.model.Person;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PersonDTOMapper implements Function<Person, PersonDto> {
    @Override
    public PersonDto apply(Person person) {
        return new PersonDto(
                person.getName(),
                new ProfessionDto(person.getProfession().getDescription())
        );
    }
}
