package com.example.turnos_rotativos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "concepto_laboral")
public class ConceptoLaboral {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "hs_maximo")
    private Integer hsMaximo;

    @Column(name = "hs_minimo")
    private Integer hsMinimo;

    @Column(name = "laborable", nullable = false)
    private Boolean laborable;

    // Getters and Setters

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

    public Integer getHsMaximo() {
        return hsMaximo;
    }

    public void setHsMaximo(Integer hsMaximo) {
        this.hsMaximo = hsMaximo;
    }

    public Integer getHsMinimo() {
        return hsMinimo;
    }

    public void setHsMinimo(Integer hsMinimo) {
        this.hsMinimo = hsMinimo;
    }

    public Boolean getLaborable() {
        return laborable;
    }

    public void setLaborable(Boolean laborable) {
        this.laborable = laborable;
    }
}