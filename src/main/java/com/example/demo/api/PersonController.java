package com.example.demo.api;

import com.example.demo.dto.PersonDTO;
import com.example.demo.model.Car;
import com.example.demo.model.Person;
import com.example.demo.model.Punto;
import com.example.demo.model.Truck;
import com.example.demo.service.PersonService;
import com.example.demo.service.Vehicle;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public PersonDTO addPerson(@Valid @NonNull @RequestBody Person person)
    {
        return personService.addPerson(person);
    }

    @GetMapping
    public List<PersonDTO> getAllPeople()
    {
        return personService.getAllPeople();
    }

    @GetMapping("/{id}")
    public PersonDTO getPeopleById(@PathVariable("id") int id)
    {
        return personService.getPeopleById(id);
    }

    @GetMapping("/names")
    @SneakyThrows
    public ResponseEntity<String> getNamesByChar(@RequestParam("char") String character) {

        return personService.getNamesByChar(character);
    }

    @GetMapping("/vehicles")
    public void getVehicle() {

        Vehicle a = new Car();
        Vehicle b = new Truck();
        Vehicle c = new Punto();

        List<Vehicle> vehicleList = List.of(a, b, c);

        vehicleList.forEach(Vehicle::getVehicleType);
    }

    @GetMapping("/numb")
    public void getStreamNumb() {

        personService.testStreamNumber();
    }

    @GetMapping("/per")
    public void getStreamPer() {

        personService.testStreamPerson();
    }
}

