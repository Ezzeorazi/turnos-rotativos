package com.example.turnos_rotativos.service.impl;

import com.example.turnos_rotativos.entity.ConceptoLaboral;
import com.example.turnos_rotativos.repository.ConceptoLaboralRepository;
import com.example.turnos_rotativos.service.ConceptoLaboralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConceptoLaboralServiceImpl implements ConceptoLaboralService {

    @Autowired
    private ConceptoLaboralRepository conceptoLaboralRepository;

    @Override
    public List<ConceptoLaboral> obtenerTodosLosConceptos() {
        return conceptoLaboralRepository.findAll();
    }

    @Override
    public List<ConceptoLaboral> buscarConceptosPorNombre(String nombre) {
        return conceptoLaboralRepository.findByNombreContaining(nombre);
    }

    @Override
    public List<ConceptoLaboral> buscarConceptosPorId(Long id) {
        return conceptoLaboralRepository.findById(id).stream().toList();
    }
}