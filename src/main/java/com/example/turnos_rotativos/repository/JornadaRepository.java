package com.example.turnos_rotativos.repository;

import com.example.turnos_rotativos.entity.Jornada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface JornadaRepository extends JpaRepository<Jornada, Long> {
    List<Jornada> findByFechaBetween(LocalDate fechaDesde, LocalDate fechaHasta);

    List<Jornada> findByIdEmpleado(Long idEmpleado);

    List<Jornada> findByIdEmpleadoAndFechaBetween(Long idEmpleado, LocalDate fechaDesde, LocalDate fechaHasta);

    List<Jornada> findByIdEmpleadoAndFecha(Long idEmpleado, LocalDate fecha);
}