package com.example.demo.mapper;

import com.example.demo.dto.PersonDTO;
import com.example.demo.record.PersonRecord;
import com.example.demo.record.ProfessionRecord;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PersonRecordMapper implements Function<PersonDTO, PersonRecord> {
    @Override
    public PersonRecord apply(PersonDTO personDTO) {
        return new PersonRecord(
                personDTO.getName(),
                new ProfessionRecord(personDTO.getProfession().getDescription())
        );
    }
}
