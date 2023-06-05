package com.gestion.empleados.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "MedicosProveedor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MedicosProveedor {
    
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedicoProveedor;
    
    @Column(name="nombreMedicoProveedor")
    private String nombreMedicoProveedor;
    
    @Column(name="matriculaMedico")
    private String matriculaMedicoProveedor;
     
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @JoinColumn(name = "proveedorAptitudId")
    private ProveedoresAptitudes proveedoresAptitudes;




    public Integer getIdMedicoProveedor() {
        return idMedicoProveedor;
    }

    public void setIdMedicoProveedor(Integer idMedicoProveedor) {
        this.idMedicoProveedor = idMedicoProveedor;
    }

    public String getNombreMedicoProveedor() {
        return nombreMedicoProveedor;
    }

    public void setNombreMedicoProveedor(String nombreMedicoProveedor) {
        this.nombreMedicoProveedor = nombreMedicoProveedor;
    }

    public String getMatriculaMedicoProveedor() {
        return matriculaMedicoProveedor;
    }

    public void setMatriculaMedicoProveedor(String matriculaMedicoProveedor) {
        this.matriculaMedicoProveedor = matriculaMedicoProveedor;
    }

    public ProveedoresAptitudes getProveedoresAptitudes() {
        return proveedoresAptitudes;
    }

    public void setProveedoresAptitudes(ProveedoresAptitudes proveedoresAptitudes) {
        this.proveedoresAptitudes = proveedoresAptitudes;
    }
    
    
    
}
