package com.example.demo.dto;

import com.example.demo.model.Profession;

public record PersonDto (
        int id,
        String name,
        Profession profession
) {

}
