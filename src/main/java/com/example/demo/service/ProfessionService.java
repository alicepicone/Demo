package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.dao.ProfessionDao;
import com.example.demo.dto.ProfessionDTOMapper;
import com.example.demo.dto.ProfessionDto;
import com.example.demo.model.Person;
import com.example.demo.model.Profession;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessionService {

    private final ProfessionDao professionDao;
    private final ProfessionDTOMapper professionDTOMapper;
    private final PersonDao personDao;

    @Autowired
    public ProfessionService(ProfessionDao professionDao, ProfessionDTOMapper professionDTOMapper, PersonDao personDao) {
        this.professionDao = professionDao;
        this.professionDTOMapper = professionDTOMapper;
        this.personDao = personDao;
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

    public Profession addProfession(Profession profession)
    {
        return professionDao.save(profession);
    }

    public List<ProfessionDto> getAllProfession() {
        return professionDao.findAll()
                .stream()
                .map(professionDTOMapper)
                .collect(Collectors.toList());
    }

    public Optional<ProfessionDto> getProfessionById(int id)
    {
        return professionDao.findById(id)
                .map(professionDTOMapper);
    }

    public void deleteProfession(int id) {
       professionDao.deleteById(id);
    }

    public void updateProfessionNameById(int id, String newProfession)
    {
        Profession profession = professionDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profession not found"));
        profession.setDescription(newProfession);
        professionDao.save(profession);
    }
}
