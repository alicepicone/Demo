package com.example.demo.mapper;

import com.example.demo.dto.ProfessionDTO;
import com.example.demo.record.ProfessionRecord;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProfessionDTOMapper implements Function<ProfessionRecord, ProfessionDTO> {

    @Override
    public ProfessionDTO apply(ProfessionRecord profession) {
        return new ProfessionDTO(profession.description());
    }
}
