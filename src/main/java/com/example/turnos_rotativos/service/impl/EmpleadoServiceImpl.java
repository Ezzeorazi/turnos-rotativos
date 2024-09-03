package com.example.turnos_rotativos.service.impl;

import com.example.turnos_rotativos.dto.EmpleadoDTO;
import com.example.turnos_rotativos.entity.Empleado;
import com.example.turnos_rotativos.exceptions.BussinessException;
import com.example.turnos_rotativos.repository.EmpleadoRepository;
import com.example.turnos_rotativos.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO) {
        // Validaciones
        if (empleadoDTO.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new BussinessException("La fecha de nacimiento no puede ser posterior al día de la fecha.");
        }

        if (empleadoDTO.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new BussinessException("La fecha de ingreso no puede ser posterior al día de la fecha.");
        }

        if (empleadoDTO.getFechaNacimiento().isAfter(LocalDate.now().minusYears(18))) {
            throw new BussinessException("La edad del empleado no puede ser menor a 18 años.");
        }

        if (empleadoRepository.existsByNroDocumento(empleadoDTO.getNroDocumento())) {
            throw new BussinessException("Ya existe un empleado con el documento ingresado.");
        }

        if (empleadoRepository.existsByEmail(empleadoDTO.getEmail())) {
            throw new BussinessException("Ya existe un empleado con el email ingresado.");
        }

        // Conversión de DTO a Entidad
        Empleado empleado = empleadoDTO.toEntity();

        // Guardar empleado en la base de datos
        Empleado empleadoGuardado = empleadoRepository.save(empleado);

        // Convertir entidad guardada a DTO para devolverla
        return new EmpleadoDTO(empleadoGuardado);
    }

    @Override
    public EmpleadoDTO obtenerEmpleadoPorId(Long id) {
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(id);
        if (empleadoOpt.isPresent()) {
            return new EmpleadoDTO(empleadoOpt.get());
        } else {
            throw new BussinessException("Empleado no encontrado.");
        }
    }

    @Override
    public List<EmpleadoDTO> obtenerTodosLosEmpleados() {
        List<Empleado> empleados = empleadoRepository.findAll();
        return empleados.stream().map(EmpleadoDTO::new).collect(Collectors.toList());
    }

    @Override
    public void eliminarEmpleado(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new BussinessException("Empleado no encontrado.");
        }
        empleadoRepository.deleteById(id);
    }
}
