package com.example.turnos_rotativos.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "jornada")
public class Jornada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idEmpleado;

    @Column(nullable = false)
    private Long idConcepto;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private Integer hsTrabajadas;

    @ManyToOne
    @JoinColumn(name = "idEmpleado", insertable = false, updatable = false)
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "idConcepto", insertable = false, updatable = false)
    private ConceptoLaboral conceptoLaboral;


    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Long getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(Long idConcepto) {
        this.idConcepto = idConcepto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getHsTrabajadas() {
        return hsTrabajadas;
    }

    public void setHsTrabajadas(Integer hsTrabajadas) {
        this.hsTrabajadas = hsTrabajadas;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public ConceptoLaboral getConceptoLaboral() {
        return conceptoLaboral;
    }

    public void setConceptoLaboral(ConceptoLaboral conceptoLaboral) {
        this.conceptoLaboral = conceptoLaboral;
    }
}