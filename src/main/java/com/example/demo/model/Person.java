package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final int id;
    @NotBlank
    private final String name;

    public Person(@JsonProperty("id") int id,
                  @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
