package com.java.springbootweb.dao;

import com.java.springbootweb.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonaDao {

    List<Persona> findAll();

    void save(Persona persona);

    Optional<Persona> findById(int id);

    void delete(int id);

    Optional<Persona> findByNombre(String name);

    void setPersonaName(int id, String nombre);
}
