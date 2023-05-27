package com.gestion.empleados.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Puesto")
public class Puesto {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer idPuesto;
   
   @Column(name="nombrePuesto", length = 50, nullable = false)
   private String  nombrePuesto;
   
   @Column(name="departamento", length = 50)
   private String  departamento;
   
   @Column(name="descripcionPuesto", length = 100)
   private String  descripcionPuesto;
   
   @Column(name="JornadaLaboralPuesto", length = 50)
   private String  jornadaLaboralPuesto; 

    public Integer getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(Integer idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDescripcionPuesto() {
        return descripcionPuesto;
    }

    public void setDescripcionPuesto(String descripcionPuesto) {
        this.descripcionPuesto = descripcionPuesto;
    }

    public String getJornadaLaboralPuesto() {
        return jornadaLaboralPuesto;
    }

    public void setJornadaLaboralPuesto(String jornadaLaboralPuesto) {
        this.jornadaLaboralPuesto = jornadaLaboralPuesto;
    }
   
   
 
    
}
