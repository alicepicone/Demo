package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.dto.PersonDTO;
import com.example.demo.mapper.PersonDTOMapper;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Person;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonDao personDao;
    private final PersonDTOMapper personDTOMapper;

    @Autowired
    public PersonService(PersonDao personDao, PersonDTOMapper personDTOMapper)
    {
        this.personDao = personDao;
        this.personDTOMapper = personDTOMapper;
    }

    public PersonDTO addPerson(Person person)
    {
        personDao.save(person);
        return personDTOMapper.apply(person);
    }

    public List<PersonDTO> getAllPeople()
    {
        return personDao.findAll()
                .stream()
                .map(personDTOMapper)
                .collect(Collectors.toList());
    }

    public PersonDTO getPeopleById(int id)
    {
        return personDao.findById(id)
                .map(personDTOMapper)
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));
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

    //Esercizio con gli Stream

    public void testStreamPerson()
    {
        List<Person> personList = new ArrayList<>(personDao.findAll());

        List<Person> personStartWith = personList
                .stream()
                .filter(person -> person.getName().startsWith("a"))
                .toList();
        System.out.println("Lista di persone che iniziano con la a: "  + personStartWith);

        List<Person> personOrdineCres = personList
                .stream()
                .sorted(Comparator.comparing(Person::getDateOfBirth))
                .toList();

        System.out.println("Lista in ordine crescente:" + personOrdineCres);

        List<Person> personOrdineDecres = personList
                .stream()
                .sorted(Comparator.comparing(Person::getDateOfBirth).reversed())
                .toList();

        System.out.println("Lista in ordine decrescente:" + personOrdineDecres);

        OptionalDouble media = personList
                .stream()
                .mapToInt(person -> person.getDateOfBirth().getYear())
                .average();

        System.out.println("Media:" + media);

        Optional<Person> personaGiovane = personList
                .stream()
                .min(Comparator.comparing(Person::getDateOfBirth));

        System.out.println("Persona più giovane:" + personaGiovane);
    }

    public void testStreamNumber() {

        List<Integer> integerList = List.of(3, 9, 14, 2);

        int sum = integerList.stream()
                .reduce(0, Integer::sum);

        System.out.println(sum);

        int min = integerList.stream()
                .min(Integer::compareTo)
                .orElse(Integer.MAX_VALUE);

        System.out.println(min);

        int max = integerList.stream()
                .max(Integer::compareTo)
                .orElse(Integer.MIN_VALUE);

        System.out.println(max);
    }
}
