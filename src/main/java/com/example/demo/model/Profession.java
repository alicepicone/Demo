package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Profession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    private String description;

    @OneToMany(mappedBy = "profession")
    List<Person> persons;

    @PreRemove
    private void removeAssociationsWithChilds() {
        for (Person e : persons) {
            e.setProfession(null);
        }
    }
}
