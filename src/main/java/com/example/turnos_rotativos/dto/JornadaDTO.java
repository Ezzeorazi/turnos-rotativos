package com.example.turnos_rotativos.dto;

import com.example.turnos_rotativos.entity.Jornada;

import java.time.LocalDate;

public class JornadaDTO {
    private Long id;
    private Long idEmpleado;
    private Long idConcepto;
    private Integer nroDocumento;
    private String nombreCompleto;
    private LocalDate fecha;
    private String concepto;
    private Integer hsTrabajadas;

    public JornadaDTO() {}

    public JornadaDTO(Jornada jornada) {
        this.id = jornada.getId();
        this.idEmpleado = jornada.getIdEmpleado();
        this.idConcepto = jornada.getIdConcepto();
        this.nroDocumento = jornada.getEmpleado() != null ? jornada.getEmpleado().getNroDocumento() : null;
        this.nombreCompleto = jornada.getEmpleado() != null ? jornada.getEmpleado().getNombre() + " " + jornada.getEmpleado().getApellido() : null;
        this.fecha = jornada.getFecha();
        this.concepto = jornada.getConceptoLaboral().getNombre();
        this.hsTrabajadas = jornada.getConceptoLaboral().getNombre().equals("Día Libre") ? null : jornada.getHsTrabajadas();
    }

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

    public Integer getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(Integer nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Integer getHsTrabajadas() {
        return hsTrabajadas;
    }

    public void setHsTrabajadas(Integer hsTrabajadas) {
        this.hsTrabajadas = hsTrabajadas;
    }
}