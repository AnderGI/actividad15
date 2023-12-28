package org.zabalburu.actividad15.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zabalburu.actividad15.model.Empleados;

@Repository
public interface EmpleadosRepository extends JpaRepository<Empleados, String> {

}
