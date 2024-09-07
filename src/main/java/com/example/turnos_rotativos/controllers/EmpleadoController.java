package com.example.turnos_rotativos.controllers;

import com.example.turnos_rotativos.dto.EmpleadoDTO;
import com.example.turnos_rotativos.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    // POST "/empleados/crear" para crear un empleado
    // POST "/empleados/crear" para crear un empleado con jornadas asociadas

    @PostMapping("/crear")
    public ResponseEntity<List<EmpleadoDTO>> crearEmpleados(@Valid @RequestBody List<EmpleadoDTO> empleadosDTO) {
        List<EmpleadoDTO> empleadosCreados = empleadoService.crearEmpleados(empleadosDTO);
        return new ResponseEntity<>(empleadosCreados, HttpStatus.CREATED);
    }

    // PUT "/empleados/{id}" para actualizar un empleado
    // PUT "/empleados/{id}" para actualizar un empleado con jornadas asociadas

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@PathVariable Long id, @Valid @RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoDTO empleadoActualizado = empleadoService.actualizarEmpleado(id, empleadoDTO);
        return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
    }


    // GET "/empleados/{id}" para obtener un empleado por Id

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> obtenerEmpleadoPorId(@PathVariable Long id) {
        EmpleadoDTO empleado = empleadoService.obtenerEmpleadoPorId(id);
        return new ResponseEntity<>(empleado, HttpStatus.OK);
    }

    // GET "/empleados" para obtener todos los empleados
    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> obtenerTodosLosEmpleados() {
        List<EmpleadoDTO> empleados = empleadoService.obtenerTodosLosEmpleados();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }


    // DELETE "/empleados/{id}" para eliminar un empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable Long id) {
        try {
            empleadoService.eliminarEmpleado(id);
            return new ResponseEntity<>("Empleado eliminado con éxito.", HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().contains("No se encontró el empleado con Id")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            } else if (e.getMessage().contains("No es posible eliminar un empleado con jornadas asociadas")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}