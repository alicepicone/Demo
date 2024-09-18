package com.example.demo.api;

import com.example.demo.dto.ProfessionDTO;
import com.example.demo.model.Profession;
import com.example.demo.record.ProfessionRecord;
import com.example.demo.service.ProfessionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/professions")
@RestController
public class ProfessionController {

    private final ProfessionService professionService;

    @Autowired
    public ProfessionController(ProfessionService professionService)
    {
        this.professionService = professionService;
    }

    @GetMapping("/by_person")
    public String getProfessionByPersonName(@RequestParam("name") String name)
    {
        return professionService.professionByPersonName(name);
    }

    @PostMapping
    public ProfessionDTO addProfession(@Valid @NonNull @RequestBody Profession profession)
    {
        return professionService.addProfession(profession);
    }

    @GetMapping
    public List<ProfessionRecord> getAllProfessions()
    {
        return professionService.getAllProfession();
    }

    @GetMapping("/{id}")
    public ProfessionDTO getProfessionById(@PathVariable("id") int id)
    {
        return professionService.getProfessionById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProfessionById(@PathVariable("id") int id) {
        professionService.deleteProfession(id);
    }

    @PutMapping("/{id}")
    public ProfessionDTO updateProfessionName(@PathVariable("id") int id, @RequestBody Profession profession)
    {
        return professionService.updateProfessionNameById(id, profession.getDescription());
    }

}