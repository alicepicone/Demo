package com.example.demo.mapper;

import com.example.demo.dto.ProfessionDTO;
import com.example.demo.record.ProfessionRecord;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProfessionRecordMapper implements Function<ProfessionDTO, ProfessionRecord> {
    @Override
    public ProfessionRecord apply(ProfessionDTO professionDTO) {
        return new ProfessionRecord(
                professionDTO.getDescription()
        );
    }
}
