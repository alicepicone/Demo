package com.example.demo.dao;

import com.example.demo.model.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionDao extends JpaRepository<Profession, Integer> {

}