package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Resena;

public interface ResenaRepository extends JpaRepository<Resena, Integer> {
   
}
