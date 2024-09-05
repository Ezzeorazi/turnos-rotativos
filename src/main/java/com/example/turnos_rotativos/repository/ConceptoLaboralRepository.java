package com.example.turnos_rotativos.repository;

import com.example.turnos_rotativos.entity.ConceptoLaboral;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConceptoLaboralRepository extends JpaRepository<ConceptoLaboral, Long> {
    List<ConceptoLaboral> findByNombreContaining(String nombre);
}
