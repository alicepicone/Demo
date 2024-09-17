package com.example.demo.mapper;

import com.example.demo.dto.PersonDto;
import com.example.demo.dto.ProfessionDto;
import com.example.demo.model.Person;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PersonDTOMapper implements Function<Person, PersonDto> {
    @Override
    public PersonDto apply(Person person) {
        return new PersonDto(
                person.getName(),
                new ProfessionDto(person.getProfession().getDescription())
        );
    }
}
