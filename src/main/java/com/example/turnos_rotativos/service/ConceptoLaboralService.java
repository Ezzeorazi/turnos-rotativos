package com.example.turnos_rotativos.service;

import com.example.turnos_rotativos.entity.ConceptoLaboral;

import java.util.List;

public interface ConceptoLaboralService {
    List<ConceptoLaboral> obtenerTodosLosConceptos();
    List<ConceptoLaboral> buscarConceptosPorNombre(String nombre);
    List<ConceptoLaboral> buscarConceptosPorId(Long id);

}