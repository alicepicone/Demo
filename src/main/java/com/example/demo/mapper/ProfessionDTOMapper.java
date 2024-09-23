package com.example.demo.mapper;

import com.example.demo.dto.ProfessionDTO;
import com.example.demo.model.Profession;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProfessionDTOMapper implements Function<Profession, ProfessionDTO> {

    @Override
    public ProfessionDTO apply(Profession profession) {
        return new ProfessionDTO(profession.getDescription());
    }
}
