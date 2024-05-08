package com.teetech.teetech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teetech.teetech.TShirt;

@Repository
public interface TShirtRepository extends JpaRepository<TShirt, Long> {
    // No es necesario agregar ningún método aquí a menos que necesites operaciones de base de datos personalizadas.
    // JpaRepository ya proporciona métodos CRUD básicos y búsqueda por ID.
}
