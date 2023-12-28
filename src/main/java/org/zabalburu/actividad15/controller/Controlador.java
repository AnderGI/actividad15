package org.zabalburu.actividad15.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.zabalburu.actividad15.model.Empleados;
import org.zabalburu.actividad15.model.Tareas;
import org.zabalburu.actividad15.service.Servicio;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class Controlador {
    @Autowired
    private Servicio servicio;

    private ResponseEntity<?> handleGetRequests(Object o) {
        if (o == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(o);
    }

    @GetMapping("/empleados")
    public ResponseEntity<?> getEmpleados() {
        List<Empleados> empleados = servicio.getEmpleados();
        return handleGetRequests((List<Empleados>) empleados);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<?> getEmpleado(@PathVariable String id) {
        Empleados emp = servicio.getEmpleadoPorId(id);
        return handleGetRequests((Empleados) emp);
    }

    @GetMapping("/empleados/{idEmpleado}/tareas")
    public ResponseEntity<?> getTareasEmpleado(@PathVariable String idEmpleado) {
        List<Tareas> tareas = servicio.getTareasEmpleadoDesc(idEmpleado);
        return handleGetRequests((List<Tareas>) tareas);
    }

    @GetMapping("/empleados/{idEmpleado}/pendientes")
    public ResponseEntity<?> getTareasPendientes(@PathVariable String idEmpleado) {
        List<Tareas> tareasPendientes = servicio.getTareasPendientesUsuario(idEmpleado);
        return handleGetRequests((List<Tareas>) tareasPendientes);
    }

    @GetMapping("/empleados/{idEmpleado}/tareas/{idTarea}")
    public ResponseEntity<?> getTareaDeEmpleado(@PathVariable String idEmpleado, @PathVariable Integer idTarea) {
        Tareas t = servicio.getTareasDeUsuario(idEmpleado, idTarea);
        return handleGetRequests((Tareas) t);
    }

    // MÉTODOS POST
    @PostMapping(path = "/empleados/{idEmpleado}/tareas", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addTarea(@PathVariable String idEmpleado,
            @Valid @RequestBody Tareas tarea) throws URISyntaxException {
        Tareas t = servicio.nuevaTarea(idEmpleado, tarea);
        if (t == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.created(
                new URI("/empleados/" + idEmpleado + "/tareas/" + t.getId())).build();
    }

    // MÉTODOS PUT
    @PutMapping(path = "/empleados/{idEmpleado}/tareas/{idTarea}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modificarTarea(@PathVariable String idEmpleado,
            @PathVariable Integer idTarea, @Valid @RequestBody Tareas tarea) {
        Empleados emp = (Empleados) getEmpleado(idEmpleado).getBody();
        if (emp == null)
            return ResponseEntity.notFound().build();
        // Si existe el empleado se lo asignamos a la tarea
        tarea.setEmpleadoResponsable(emp);
        tarea.setId(idTarea);
        Tareas t = servicio.modificarTarea(tarea);
        if (t == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(t);
    }

    // MÉTODO DELETE
    @DeleteMapping("/empleados/{idUsuario}/tareas/{idTarea}")
    public ResponseEntity<?> eliminarTarea(@PathVariable String idUsuario,
            @PathVariable Integer idTarea) {
        Tareas tarea = (Tareas) getTareaDeEmpleado(idUsuario, idTarea).getBody();
        if (tarea == null)
            return ResponseEntity.notFound().build();
        servicio.eliminarTarea(tarea);
        return ResponseEntity.ok().build();
    }

}
