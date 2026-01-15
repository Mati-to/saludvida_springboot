package com.matiasac.saludvida_backend.model.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String nombre;

    protected Especialidad() {
    }

    public Especialidad(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Especialidad: " + nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
