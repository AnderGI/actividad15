package org.zabalburu.actividad15.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Empleadotareas")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class EmpleadoTareas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "idempleado", insertable = false, updatable = false)
    private Empleados idEmpleado;
    @ManyToOne
    @JoinColumn(name = "idtarea", insertable = false, updatable = false)
    private Tareas idTarea;
}
