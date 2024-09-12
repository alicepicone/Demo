package com.example.demo.dto;

import com.example.demo.model.Profession;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProfessionDTOMapper implements Function<Profession, ProfessionDto> {
    @Override
    public ProfessionDto apply(Profession profession) {
        return new ProfessionDto(
                profession.getId(),
                profession.getDescription()
        );
    }
}
