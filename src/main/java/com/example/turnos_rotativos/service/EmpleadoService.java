package com.example.turnos_rotativos.service;

import com.example.turnos_rotativos.dto.EmpleadoDTO;
import com.example.turnos_rotativos.exceptions.BussinessException;

import java.util.List;

public interface EmpleadoService {
    EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO);
    EmpleadoDTO actualizarEmpleado(Long id, EmpleadoDTO empleadoDTO);
    EmpleadoDTO obtenerEmpleadoPorId(Long id);
    List<EmpleadoDTO> obtenerTodosLosEmpleados();
    void eliminarEmpleado(Long id) throws Exception;
    List<EmpleadoDTO> crearEmpleados(List<EmpleadoDTO> empleadosDTO); // Add this method
}