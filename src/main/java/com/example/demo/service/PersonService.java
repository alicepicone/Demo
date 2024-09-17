package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.mapper.PersonRecordMapper;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Person;
import com.example.demo.record.PersonRecord;
import io.micrometer.common.util.StringUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonDao personDao;
    private final PersonRecordMapper personRecordMapper;

    @Autowired
    public PersonService(PersonDao personDao, PersonRecordMapper personRecordMapper)
    {
        this.personDao = personDao;
        this.personRecordMapper = personRecordMapper;
    }

    public Person addPerson(Person person)
    {
        return personDao.save(person);
    }

    public List<PersonRecord> getAllPeople()
    {
        return personDao.findAll()
                .stream()
                .map(personRecordMapper)
                .collect(Collectors.toList());
    }

    public Optional<PersonRecord> getPeopleById(int id)
    {
        return personDao.findById(id)
                .map(personRecordMapper);
    }

    public boolean isValidCharacter(String character) {
        return StringUtils.isNotBlank(character) && character.matches("[a-zA-Z]");
    }

    public String getNamesStartingWith(String character) {
        List<Person> persons = personDao.findByNameStartingWithIgnoreCase(character);

        StringBuilder sb = new StringBuilder();
        if (!persons.isEmpty()) {
            for (Person person : persons) {
                sb.append(person.getName()).append(", ");
            }
            // cancella l'ultima virgola e lo spazio
            sb.deleteCharAt(sb.length() - 2);
        }
        return sb.toString();
    }

    @SneakyThrows
    public ResponseEntity<String> getNamesByChar(String character) {

        // Validazione dell'input
        if (!isValidCharacter(character)) {
            throw new BadRequestException("Invalid input");
        }
        // Recupero dei nomi dal database
        String names = getNamesStartingWith(character);

        if (names.isEmpty()) {
            throw new NotFoundException("Nessun nome trovato che inizi con la lettera " + character);
        }
        return ResponseEntity.ok(names);
    }
}
