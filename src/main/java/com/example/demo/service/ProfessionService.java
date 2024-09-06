package com.example.demo.service;

import com.example.demo.dao.ProfessionDao;
import com.example.demo.model.Profession;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessionService {

    private final ProfessionDao professionDao;

    @Autowired
    public ProfessionService(ProfessionDao professionDao) {
        this.professionDao = professionDao;
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
