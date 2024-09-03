package com.example.turnos_rotativos.service;

import com.example.turnos_rotativos.dto.EmpleadoDTO;

import java.util.List;

public interface EmpleadoService {
    EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO);
    EmpleadoDTO obtenerEmpleadoPorId(Long id);
    List<EmpleadoDTO> obtenerTodosLosEmpleados();
    void eliminarEmpleado(Long id);
}

