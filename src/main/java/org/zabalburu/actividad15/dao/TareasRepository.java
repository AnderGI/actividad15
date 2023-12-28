package org.zabalburu.actividad15.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zabalburu.actividad15.model.Tareas;

@Repository
public interface TareasRepository extends JpaRepository<Tareas, Integer> {
        @Query("Select t From Tareas t where t.empleadoResponsable.id = :id order by t.id desc")
        List<Tareas> findTareasByUsuarioIdDesc(@Param("id") String idEmpleao);

        @Query("""
                        SELECT t FROM Tareas t WHERE t.empleadoResponsable.id = :id
                        AND t.realizada = 0 ORDER BY t.id desc
                        """)
        List<Tareas> findTareasPendientesDeUsuarioDesc(@Param("id") String idEmpleado);

        @Query("""
                        SELECT t from Tareas t WHERE t.empleadoResponsable.id = :idEmpleado
                        AND t.id = :idTarea
                        """)
        Tareas findTareaFromEmpleado(@Param("idEmpleado") String idEmpleado, @Param("idTarea") Integer idTarea);
}
