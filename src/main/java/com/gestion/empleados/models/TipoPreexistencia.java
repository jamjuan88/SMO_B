
package com.gestion.empleados.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TipoPreexistencia")
public class TipoPreexistencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoPreexistencia;
    
    @Column(name="nombreTipoPreexistencia")
    private String nombreTipoPreexistencia;
    
    @Column(name="descripcionTipoPreexistencia")
    private String descripcionTipoPreexistencia;

    public Integer getIdTipoPreexistencia() {
        return idTipoPreexistencia;
    }

    public void setIdTipoPreexistencia(Integer idTipoPreexistencia) {
        this.idTipoPreexistencia = idTipoPreexistencia;
    }

    public String getNombreTipoPreexistencia() {
        return nombreTipoPreexistencia;
    }

    public void setNombreTipoPreexistencia(String nombreTipoPreexistencia) {
        this.nombreTipoPreexistencia = nombreTipoPreexistencia;
    }

    public String getDescripcionTipoPreexistencia() {
        return descripcionTipoPreexistencia;
    }

    public void setDescripcionTipoPreexistencia(String descripcionTipoPreexistencia) {
        this.descripcionTipoPreexistencia = descripcionTipoPreexistencia;
    }
    
    
    
}
