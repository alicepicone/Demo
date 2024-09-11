package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.dao.ProfessionDao;
import com.example.demo.model.Person;
import com.example.demo.model.Profession;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionService {

    private final ProfessionDao professionDao;
    private final PersonDao personDao;

    @Autowired
    public ProfessionService(ProfessionDao professionDao, PersonDao personDao) {
        this.professionDao = professionDao;
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

    public List<Profession> getAllProfession() {
        return professionDao.findAll();
    }

    public void deleteProfession(int id) {
       professionDao.deleteById(id);
    }

    public Profession updateProfessionNameById(int id, String newProfession)
    {
        Profession profession = professionDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profession not found"));
        profession.setDescription(newProfession);
        return professionDao.save(profession);
    }
}
