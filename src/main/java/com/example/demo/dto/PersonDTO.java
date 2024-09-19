package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PersonDTO {
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private ProfessionDTO profession;
}
