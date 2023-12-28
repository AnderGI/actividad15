package org.zabalburu.actividad15.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Table(name = "tareas")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Tareas {
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "realizada")
    private Byte realizada;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha")
    @DateTimeFormat(style = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    @FutureOrPresent
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "idusuario")
    @JsonIgnore
    private Empleados empleadoResponsable;

    @Override
    public String toString() {
        return this.titulo + ", " + this.realizada
                + " [ " + this.id + " ]";
    }

}
