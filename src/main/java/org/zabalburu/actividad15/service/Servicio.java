package org.zabalburu.actividad15.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zabalburu.actividad15.dao.EmpleadosRepository;
import org.zabalburu.actividad15.dao.TareasRepository;
import org.zabalburu.actividad15.model.Empleados;
import org.zabalburu.actividad15.model.Tareas;

import jakarta.validation.Valid;

@Service
public class Servicio {
    @Autowired
    private EmpleadosRepository empDao;
    @Autowired
    private TareasRepository tareasDao;

    private Tareas getTareaPorId(Integer idTarea) {
        return tareasDao.findById(idTarea).orElse(null);
    }

    public Empleados getEmpleadoPorId(String id) {
        return empDao.findById(id).orElse(null);
    }

    public List<Empleados> getEmpleados() {
        return empDao.findAll();
    }

    public List<Tareas> getTareasEmpleadoDesc(String idEmpleao) {
        return tareasDao.findTareasByUsuarioIdDesc(idEmpleao);
    }

    public List<Tareas> getTareasPendientesUsuario(String idEmpleado) {
        return tareasDao.findTareasPendientesDeUsuarioDesc(idEmpleado);
    }

    public Tareas getTareasDeUsuario(String idEmpleado, Integer idTarea) {
        return tareasDao.findTareaFromEmpleado(idEmpleado, idTarea);
    }

    public Tareas nuevaTarea(String idEmpleado, Tareas t) {
        Empleados emp = getEmpleadoPorId(idEmpleado);
        if (emp != null) {
            t.setEmpleadoResponsable(emp);
            return tareasDao.save(t);
        }
        return null;
    }

    public Tareas modificarTarea(@Valid Tareas tarea) {
        // Comprobamos si la tarea existe o no
        // Al ser un PUT es necesario que exista
        if (getTareaPorId(tarea.getId()) == null)
            return null;
        return tareasDao.save(tarea);
    }

    public void eliminarTarea(@Valid Tareas tarea) {
        tareasDao.delete(tarea);
    }
}
