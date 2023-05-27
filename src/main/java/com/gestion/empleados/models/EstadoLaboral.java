package com.gestion.empleados.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="EstadoLaboral")
public class EstadoLaboral {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstado;
    
    @Column(name="nombreEstado", length = 50, nullable = false)
    private String nombreEstado;
    
    @Column(name="descripcionEstado", length = 255)
    private String  descripcionEstado;

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }
    
    
    
    
}
