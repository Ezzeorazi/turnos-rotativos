// src/main/java/com/example/turnos_rotativos/service/impl/JornadaServiceImpl.java

package com.example.turnos_rotativos.service.impl;

import com.example.turnos_rotativos.dto.JornadaDTO;
import com.example.turnos_rotativos.entity.ConceptoLaboral;
import com.example.turnos_rotativos.entity.Empleado;
import com.example.turnos_rotativos.entity.Jornada;
import com.example.turnos_rotativos.repository.ConceptoLaboralRepository;
import com.example.turnos_rotativos.repository.EmpleadoRepository;
import com.example.turnos_rotativos.repository.JornadaRepository;
import com.example.turnos_rotativos.service.JornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JornadaServiceImpl implements JornadaService {

    @Autowired
    private JornadaRepository jornadaRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private ConceptoLaboralRepository conceptoLaboralRepository;


    @Override
    public JornadaDTO crearJornada(JornadaDTO jornadaDTO) throws Exception {
        // Validations
        if (jornadaDTO.getIdEmpleado() == null) {
            throw new Exception("'idEmpleado' es obligatorio.");
        }
        if (jornadaDTO.getIdConcepto() == null) {
            throw new Exception("'idConcepto' es obligatorio.");
        }
        if (jornadaDTO.getFecha() == null) {
            throw new Exception("'fecha' es obligatorio.");
        }

        Empleado empleado = empleadoRepository.findById(jornadaDTO.getIdEmpleado())
                .orElseThrow(() -> new Exception("No existe el empleado ingresado."));
        ConceptoLaboral concepto = conceptoLaboralRepository.findById(jornadaDTO.getIdConcepto())
                .orElseThrow(() -> new Exception("No existe el concepto ingresado."));

        if ((concepto.getNombre().equals("Turno Normal") || concepto.getNombre().equals("Turno Extra")) && jornadaDTO.getHsTrabajadas() == null) {
            throw new Exception("'horasTrabajadas' es obligatorio para el concepto ingresado.");
        }
        if (concepto.getNombre().equals("Día Libre") && jornadaDTO.getHsTrabajadas() != null) {
            throw new Exception("El concepto ingresado no requiere el ingreso de 'horasTrabajadas'.");
        }

        // Business rules validations
        List<Jornada> jornadasDelDia = jornadaRepository.findByIdEmpleadoAndFecha(jornadaDTO.getIdEmpleado(), jornadaDTO.getFecha());
        if (jornadasDelDia.stream().anyMatch(j -> j.getConceptoLaboral().getNombre().equals("Día Libre"))) {
            throw new Exception("El empleado ingresado cuenta con un día libre en esa fecha.");
        }
        if (jornadasDelDia.size() >= 2) {
            throw new Exception("Ya existen 2 empleados registrados para este concepto en la fecha ingresada.");
        }
        if (jornadasDelDia.stream().anyMatch(j -> j.getConceptoLaboral().getId().equals(concepto.getId()))) {
            throw new Exception("El empleado ya tiene registrado una jornada con este concepto en la fecha ingresada.");
        }

        Jornada jornada = new Jornada();
        jornada.setIdEmpleado(jornadaDTO.getIdEmpleado());
        jornada.setIdConcepto(jornadaDTO.getIdConcepto());
        jornada.setFecha(jornadaDTO.getFecha());
        jornada.setHsTrabajadas(jornadaDTO.getHsTrabajadas());
        jornada.setEmpleado(empleado); // Ensure this is set
        jornada.setConceptoLaboral(concepto); // Ensure this is set

        Jornada savedJornada = jornadaRepository.save(jornada);
        return new JornadaDTO(savedJornada);
    }

    @Override
    public List<JornadaDTO> obtenerJornadas(LocalDate fechaDesde, LocalDate fechaHasta, Long nroDocumento) throws Exception {
        if (fechaDesde != null && fechaHasta != null && fechaDesde.isAfter(fechaHasta)) {
            throw new Exception("El campo ‘fechaDesde’ no puede ser mayor que ‘fechaHasta’.");
        }

        if (nroDocumento != null && !empleadoRepository.existsById(nroDocumento)) {
            throw new Exception("No existe el empleado ingresado.");
        }

        List<Jornada> jornadas;
        if (fechaDesde != null && fechaHasta != null && nroDocumento != null) {
            jornadas = jornadaRepository.findByIdEmpleadoAndFechaBetween(nroDocumento, fechaDesde, fechaHasta);
        } else if (fechaDesde != null && fechaHasta != null) {
            jornadas = jornadaRepository.findByFechaBetween(fechaDesde, fechaHasta);
        } else if (nroDocumento != null) {
            jornadas = jornadaRepository.findByIdEmpleado(nroDocumento);
        } else {
            jornadas = jornadaRepository.findAll();
        }

        return jornadas.stream().map(JornadaDTO::new).collect(Collectors.toList());
    }
}