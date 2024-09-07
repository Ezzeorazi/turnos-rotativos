package com.example.turnos_rotativos.controllers;

import com.example.turnos_rotativos.entity.ConceptoLaboral;
import com.example.turnos_rotativos.service.ConceptoLaboralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/concepto")
public class ConceptoLaboralController {

    @Autowired
    private ConceptoLaboralService conceptoLaboralService;

    // GET "/concepto" para obtener todos los conceptos precargados en data.sql

    @GetMapping
    public ResponseEntity<List<ConceptoLaboral>> obtenerTodosLosConceptos(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String nombre) {
        List<ConceptoLaboral> conceptos;

        if (id != null && nombre != null) {
            conceptos = conceptoLaboralService.buscarConceptosPorId(id).stream()
                    .filter(concepto -> concepto.getNombre().contains(nombre))
                    .collect(Collectors.toList());
        } else if (id != null) {
            conceptos = conceptoLaboralService.buscarConceptosPorId(id);
        } else if (nombre != null) {
            conceptos = conceptoLaboralService.buscarConceptosPorNombre(nombre);
        } else {
            conceptos = conceptoLaboralService.obtenerTodosLosConceptos();
        }

        // Filter out null values for hsMinimo and hsMaximo
        conceptos.forEach(concepto -> {
            if (concepto.getHsMaximo() == null) {
                concepto.setHsMaximo(null);
            }
            if (concepto.getHsMinimo() == null) {
                concepto.setHsMinimo(null);
            }
        });

        return new ResponseEntity<>(conceptos, HttpStatus.OK);
    }
}