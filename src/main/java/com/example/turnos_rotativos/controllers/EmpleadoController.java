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

    @PostMapping("/crear")
    public ResponseEntity<EmpleadoDTO> crearEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoDTO empleadoCreado = empleadoService.crearEmpleado(empleadoDTO);
        return new ResponseEntity<>(empleadoCreado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@PathVariable Long id, @Valid @RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoDTO empleadoActualizado = empleadoService.actualizarEmpleado(id, empleadoDTO);
        return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> obtenerEmpleadoPorId(@PathVariable Long id) {
        EmpleadoDTO empleado = empleadoService.obtenerEmpleadoPorId(id);
        return new ResponseEntity<>(empleado, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> obtenerTodosLosEmpleados() {
        List<EmpleadoDTO> empleados = empleadoService.obtenerTodosLosEmpleados();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
        return new ResponseEntity<>("El empleado fue eliminado con éxito.", HttpStatus.OK);
    }
}
