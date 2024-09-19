package com.example.demo.mapper;

import com.example.demo.dto.PersonDTO;
import com.example.demo.dto.ProfessionDTO;
import com.example.demo.model.Person;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PersonDTOMapper implements Function<Person, PersonDTO> {

    @Override
    public PersonDTO apply(Person person) {
        return new PersonDTO(
                person.getName(),
                person.getSurname(),
                person.getDateOfBirth(),
                new ProfessionDTO(person.getProfession().getDescription())
        );
    }

}
