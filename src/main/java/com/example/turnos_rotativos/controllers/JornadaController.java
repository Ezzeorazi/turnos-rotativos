package com.example.turnos_rotativos.controllers;

import com.example.turnos_rotativos.dto.JornadaDTO;
import com.example.turnos_rotativos.entity.Jornada;
import com.example.turnos_rotativos.service.JornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/jornada")
public class JornadaController {

    @Autowired
    private JornadaService jornadaService;

    // POST "/jornada" para crear una jornada
    @PostMapping
    public ResponseEntity<?> crearJornada(@RequestBody JornadaDTO jornadaDTO) {
        try {
            if (jornadaDTO.getHsTrabajadas() == null) {
                return new ResponseEntity<>("El campo hsTrabajadas no puede ser nulo.", HttpStatus.BAD_REQUEST);
            }
            JornadaDTO nuevaJornada = jornadaService.crearJornada(jornadaDTO);
            return new ResponseEntity<>(nuevaJornada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // GET "/jornada" para obtener todas las jornadas
    // GET "/jornada?fechaDesde=yyyy-MM-dd&fechaHasta=yyyy-MM-dd&nroDocumento=12345678" para obtener jornadas filtradas por fecha y nroDocumento
    // GET "/jornada?fechaDesde=yyyy-MM-dd&fechaHasta=yyyy-MM-dd" para obtener jornadas filtradas por fecha
    // GET "/jornada?nroDocumento=12345678" para obtener jornadas filtradas por nroDocumento
    @GetMapping
    public ResponseEntity<?> obtenerJornadas(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta,
            @RequestParam(required = false) Long nroDocumento) {
        try {
            List<JornadaDTO> jornadas = jornadaService.obtenerJornadas(fechaDesde, fechaHasta, nroDocumento);
            return new ResponseEntity<>(jornadas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}