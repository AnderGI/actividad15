package org.zabalburu.actividad15.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Table(name = "employee")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Empleados implements Serializable {
    @Id
    @Column(name = "emp_id")
    @EqualsAndHashCode.Include
    private String id;
    @Column(name = "fname")
    private String fname;
    @Column(name = "lname")
    private String lname;
    @OneToMany(mappedBy = "empleadoResponsable")
    @JsonIgnore
    private List<Tareas> tareas;

    @Override
    public String toString() {
        return this.fname + ", " + this.lname + " [ " + this.id + " ]";
    }

}
