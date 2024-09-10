package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonService(PersonDao personDao)
    {
        this.personDao = personDao;
    }

    public Person addPerson(Person person)
    {
        return personDao.save(person);
    }

    public List<Person> getAllPeople()
    {
        return personDao.findAll();
    }

    public String professionByPersonName(String name)
    {
        Person person = personDao.findByName(name);

        if(person == null)
        {
            return "name doesn't exist!";
        }
        return person.getProfession().getDescription();
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

    public ResponseEntity<String> getNamesByChar(String character) {
        // Validazione dell'input
        if (!isValidCharacter(character)) {
            return ResponseEntity.badRequest().body("Invalid input");
        }
        // Recupero dei nomi dal database
        String names = getNamesStartingWith(character);

        if (names.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(names);
    }
}
