package com.matiasac.saludvida_backend.model.entity;

import com.matiasac.saludvida_backend.model.enums.SexoPaciente;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, length = 11)
    private String rut;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SexoPaciente sexo;

    @Column(length = 11)
    private String telefono;


    public Paciente() {
    }

    public Paciente(String nombre, String apellido, String rut, LocalDate fechaNacimiento, SexoPaciente sexo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public SexoPaciente getSexo() {
        return sexo;
    }

    public void setSexo(SexoPaciente sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
