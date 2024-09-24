package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private LocalDate dateOfBirth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profession_id")
    private Profession profession;

    @Column(name = "profession_id", insertable = false, updatable = false)
    private int professionId;

    @Override
    public String toString() {
        return "{" +
                "id: " + id +
                ", name: " + name +
                ", surname: " + surname +
                ", date of birth: " + dateOfBirth +
                "}";
    }
}
