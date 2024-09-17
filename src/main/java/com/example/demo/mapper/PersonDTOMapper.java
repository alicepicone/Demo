package com.example.demo.mapper;

import com.example.demo.dto.PersonDTO;
import com.example.demo.dto.ProfessionDTO;
import com.example.demo.record.PersonRecord;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PersonDTOMapper implements Function<PersonRecord, PersonDTO> {

    @Override
    public PersonDTO apply(PersonRecord person) {
        return new PersonDTO(
                person.name(),
                new ProfessionDTO(person.profession().description())
        );
    }

}
