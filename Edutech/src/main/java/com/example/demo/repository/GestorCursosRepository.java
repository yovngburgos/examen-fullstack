package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.GestorCursos;

public interface GestorCursosRepository extends JpaRepository<GestorCursos, String> {
    

}
