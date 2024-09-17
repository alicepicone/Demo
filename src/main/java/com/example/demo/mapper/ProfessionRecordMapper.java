package com.example.demo.mapper;

import com.example.demo.model.Profession;
import com.example.demo.record.ProfessionRecord;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProfessionRecordMapper implements Function<Profession, ProfessionRecord> {
    @Override
    public ProfessionRecord apply(Profession profession) {
        return new ProfessionRecord(
                profession.getDescription()
        );
    }
}
