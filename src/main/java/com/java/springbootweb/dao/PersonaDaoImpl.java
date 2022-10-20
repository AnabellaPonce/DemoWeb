package com.java.springbootweb.dao;

import com.java.springbootweb.entities.Persona;
import com.java.springbootweb.repo.IPersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaDaoImpl implements PersonaDao{
    @Autowired
    private IPersonaRepo personarepo;

    @Override
    public List<Persona> findAll() {
        return personarepo.findAll();
    }

    @Override
    public void save(Persona persona) {
        personarepo.save(persona);
    }

    @Override
    public Optional<Persona> findById(int id) {
        return personarepo.findById(id);
    }
    @Override
    public Optional<Persona> findByNombre(String name) {

        return personarepo.findByNombre(name);
    }

    @Override
    public void setPersonaName(int id, String nombre) {
        personarepo.setPersonaName(id, nombre);
    }

    @Override
    public void delete(int id) {

        personarepo.deleteById( id);
    }
}

