package com.example.turnos_rotativos.repository;

import com.example.turnos_rotativos.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    boolean existsByNroDocumento(Integer nroDocumento);

    boolean existsByEmail(String email);
}

