package com.java.springbootweb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
public class Persona {
    @Id
    private int idPersona;
    @Column(name = "nombre", length = 50)
    private String nombre;


    @Column(name = "apellido", length = 50)
    private String apellido;
    @JsonIgnore
    @Column(name = "edad", length = 10, nullable = true)
    private int edad;

    @Column(name = "direccion", length = 50, nullable = true)
    private String direccion;

    @Column(name = "telefono", nullable = true)
    private Integer telefono;

    public Persona(int idPersona, String nombre, String apellido, int edad) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public Persona() {

    }
}
