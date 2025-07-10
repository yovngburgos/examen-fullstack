package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Contenido;

public interface ContenidoRepository extends JpaRepository<Contenido, Integer> {
    Optional<Contenido> findByTitulo(String titulo);
}
