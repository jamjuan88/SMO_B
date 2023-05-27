
package com.gestion.empleados.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "faltas")
public class Falta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaFaltaInicio")
    private LocalDate fechaFaltaInicio;
    
    @Column(name = "fechaFaltaFinal")
    private LocalDate fechaFaltaFinal;

    @Column(name = "descripcionFalta")
    private String descripcionFalta;

    @Column(name = "tipoFalta")
    private String tipoFalta;
    
    @Column(name = "urlArhivo")
    private String urlArchivo;
    
    @ManyToOne
    @JoinColumn(name = "empleadoId")
    private Empleado empleado;
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaFaltaInicio() {
        return fechaFaltaInicio;
    }

    public void setFechaFaltaInicio(LocalDate fechaFaltaInicio) {
        this.fechaFaltaInicio = fechaFaltaInicio;
    }

    public LocalDate getFechaFaltaFinal() {
        return fechaFaltaFinal;
    }

    public void setFechaFaltaFinal(LocalDate fechaFaltaFinal) {
        this.fechaFaltaFinal = fechaFaltaFinal;
    }

    public String getDescripcionFalta() {
        return descripcionFalta;
    }

    public void setDescripcionFalta(String descripcionFalta) {
        this.descripcionFalta = descripcionFalta;
    }

    public String getTipoFalta() {
        return tipoFalta;
    }

    public void setTipoFalta(String tipoFalta) {
        this.tipoFalta = tipoFalta;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getUrlArchivo() {
        return urlArchivo;
    }

    public void setUrlArchivo(String urlArchivo) {
        this.urlArchivo = urlArchivo;
    }
 
    
  

    
    
    }
