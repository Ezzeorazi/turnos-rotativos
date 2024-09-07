package com.example.turnos_rotativos.dto;

import com.example.turnos_rotativos.entity.Empleado;


import java.time.LocalDate;
import java.util.List;

// DTO para la entidad Empleado
public class EmpleadoDTO {

    private Long id;
    private Integer nroDocumento;
    private String nombre;
    private String apellido;
    private String email;
    private LocalDate fechaNacimiento;
    private LocalDate fechaIngreso;
    private LocalDate fechaCreacion;
    private List<EmpleadoDTO> empleados;

    // Constructor vacío
    public EmpleadoDTO() {
    }

    // Constructor
    public Empleado toEntity() {
        Empleado empleado = new Empleado();
        empleado.setNroDocumento(this.nroDocumento);
        empleado.setNombre(this.nombre);
        empleado.setApellido(this.apellido);
        empleado.setEmail(this.email);
        empleado.setFechaNacimiento(this.fechaNacimiento);
        empleado.setFechaIngreso(this.fechaIngreso);
        return empleado;
    }

    // Constructor con parámetros
    public EmpleadoDTO(Empleado empleado) {
        this.id = empleado.getId();
        this.nroDocumento = empleado.getNroDocumento();
        this.nombre = empleado.getNombre();
        this.apellido = empleado.getApellido();
        this.email = empleado.getEmail();
        this.fechaNacimiento = empleado.getFechaNacimiento();
        this.fechaIngreso = empleado.getFechaIngreso();
        this.fechaCreacion = empleado.getFechaCreacion();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(Integer nroDocumento) {
        this.nroDocumento = nroDocumento;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<EmpleadoDTO> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<EmpleadoDTO> empleados) {
        this.empleados = empleados;
    }


}
