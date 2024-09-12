package com.example.demo.dto;

import com.example.demo.model.Profession;

public record PersonDto (
        String name,
        Profession profession
) {

}
