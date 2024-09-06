package com.example.turnos_rotativos.service;

import com.example.turnos_rotativos.dto.JornadaDTO;
import com.example.turnos_rotativos.entity.Jornada;

import java.time.LocalDate;
import java.util.List;

public interface JornadaService {
    JornadaDTO crearJornada(JornadaDTO jornadaDTO) throws Exception;
    List<JornadaDTO> obtenerJornadas(LocalDate fechaDesde, LocalDate fechaHasta, Long nroDocumento) throws Exception;
}