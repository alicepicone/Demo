package com.example.demo.mapper;

import com.example.demo.dto.ProfessionDto;
import com.example.demo.model.Profession;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProfessionDTOMapper implements Function<Profession, ProfessionDto> {
    @Override
    public ProfessionDto apply(Profession profession) {
        return new ProfessionDto(
                profession.getDescription()
        );
    }
}
