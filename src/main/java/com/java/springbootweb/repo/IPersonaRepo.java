package com.java.springbootweb.repo;

import com.java.springbootweb.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface IPersonaRepo extends JpaRepository<Persona, Integer> {
    Optional<Persona> findByNombre(String name);
    @Transactional
    @Modifying
    @Query("update Persona p set p.nombre = :nombre WHERE p.idPersona = :id")
    void setPersonaName(@Param("id") int id, @Param("nombre") String nombre);
}
