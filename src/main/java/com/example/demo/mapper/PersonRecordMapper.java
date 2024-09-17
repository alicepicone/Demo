package com.example.demo.mapper;

import com.example.demo.model.Person;
import com.example.demo.record.PersonRecord;
import com.example.demo.record.ProfessionRecord;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PersonRecordMapper implements Function<Person, PersonRecord> {
    @Override
    public PersonRecord apply(Person person) {
        return new PersonRecord(
                person.getName(),
                new ProfessionRecord(person.getProfession().getDescription())
        );
    }
}
