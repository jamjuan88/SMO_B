
package com.gestion.empleados.models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Preexistencias")
public class Preexistencias {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPreexistencia;
    
    @Column(name="nombrePreexistencia")
    private String nombrePreexistencia;
    
    @Column(name="descripcionPreexistencia")
    private String descripcionPreexistencia;

    public Integer getIdPreexistencia() {
        return idPreexistencia;
    }

    public void setIdPreexistencia(Integer idPreexistencia) {
        this.idPreexistencia = idPreexistencia;
    }

    public String getNombrePreexistencia() {
        return nombrePreexistencia;
    }

    public void setNombrePreexistencia(String nombrePreexistencia) {
        this.nombrePreexistencia = nombrePreexistencia;
    }

    public String getDescripcionPreexistencia() {
        return descripcionPreexistencia;
    }

    public void setDescripcionPreexistencia(String descripcionPreexistencia) {
        this.descripcionPreexistencia = descripcionPreexistencia;
    }
    
    

    
}
