package com.example.turnos_rotativos.service.impl;

import com.example.turnos_rotativos.dto.EmpleadoDTO;
import com.example.turnos_rotativos.entity.Empleado;
import com.example.turnos_rotativos.exceptions.BussinessException;
import com.example.turnos_rotativos.repository.EmpleadoRepository;
import com.example.turnos_rotativos.repository.JornadaRepository;
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

    @Autowired
    private JornadaRepository jornadaRepository;

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
    public void eliminarEmpleado(Long id) throws Exception {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontró el empleado con Id: " + id));

        if (jornadaRepository.existsByIdEmpleado(id)) {
            throw new Exception("No es posible eliminar un empleado con jornadas asociadas.");
        }

        empleadoRepository.deleteById(id);
    }

    @Override
    public EmpleadoDTO actualizarEmpleado(Long id, EmpleadoDTO empleadoDTO) {
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

        Optional<Empleado> empleadoOpt = empleadoRepository.findById(id);
        if (!empleadoOpt.isPresent()) {
            throw new BussinessException("Empleado no encontrado.");
        }

        Empleado empleado = empleadoOpt.get();
        empleado.setNroDocumento(empleadoDTO.getNroDocumento());
        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setApellido(empleadoDTO.getApellido());
        empleado.setEmail(empleadoDTO.getEmail());
        empleado.setFechaNacimiento(empleadoDTO.getFechaNacimiento());
        empleado.setFechaIngreso(empleadoDTO.getFechaIngreso());

        Empleado empleadoActualizado = empleadoRepository.save(empleado);
        return new EmpleadoDTO(empleadoActualizado);
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
    public List<EmpleadoDTO> crearEmpleados(List<EmpleadoDTO> empleadosDTO) {
        List<Empleado> empleados = empleadosDTO.stream().map(this::convertToEntity).collect(Collectors.toList());
        List<Empleado> empleadosGuardados = empleadoRepository.saveAll(empleados);
        return empleadosGuardados.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private Empleado convertToEntity(EmpleadoDTO empleadoDTO) {
        Empleado empleado = new Empleado();
        empleado.setNroDocumento(empleadoDTO.getNroDocumento());
        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setApellido(empleadoDTO.getApellido());
        empleado.setEmail(empleadoDTO.getEmail());
        empleado.setFechaNacimiento(empleadoDTO.getFechaNacimiento());
        empleado.setFechaIngreso(empleadoDTO.getFechaIngreso());
        return empleado;
    }

    private EmpleadoDTO convertToDTO(Empleado empleado) {
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setId(empleado.getId());
        empleadoDTO.setNroDocumento(empleado.getNroDocumento());
        empleadoDTO.setNombre(empleado.getNombre());
        empleadoDTO.setApellido(empleado.getApellido());
        empleadoDTO.setEmail(empleado.getEmail());
        empleadoDTO.setFechaNacimiento(empleado.getFechaNacimiento());
        empleadoDTO.setFechaIngreso(empleado.getFechaIngreso());
        empleadoDTO.setFechaCreacion(empleado.getFechaCreacion());
        return empleadoDTO;
    }
}