package com.gestion.empleados.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Aptitudes")
public class Aptitudes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAptitud;
     
    @Column(name = "nombreAptitud") 
    private String nombreAptitud;
    
    @Column(name = "descripcionAptitud")
    private String descripcionAptitud;

    public Long getIdAptitud() {
        return idAptitud;
    }

    public void setIdAptitud(Long idAptitud) {
        this.idAptitud = idAptitud;
    }

    public String getNombreAptitud() {
        return nombreAptitud;
    }

    public void setNombreAptitud(String nombreAptitud) {
        this.nombreAptitud = nombreAptitud;
    }

    public String getDescripcionAptitud() {
        return descripcionAptitud;
    }

    public void setDescripcionAptitud(String descripcionAptitud) {
        this.descripcionAptitud = descripcionAptitud;
    }
    
        
}
