package com.gestion.empleados.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name= "ProveedoresAptitudes")
public class ProveedoresAptitudes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProveedorAptitud;
    
    @Column(name="nombreProveedorAptitud")
    private String nombreProveedorAptitud;
    
    @Column(name="cuitProveedorAptitud")
    private String cuitProveedorAptitud;
    
    @Column(name="localidadProveedorAptitud")
    private String localidadProveedorAptitud; 
    
     @JsonIgnore
    @OneToMany(mappedBy = "proveedoresAptitudes")
    private List<MedicosProveedor> medicosProveedor;

    
    


    
   

    public Integer getIdProveedorAptitud() {
        return idProveedorAptitud;
    }

    public void setIdProveedorAptitud(Integer idProveedorAptitud) {
        this.idProveedorAptitud = idProveedorAptitud;
    }

    public String getNombreProveedorAptitud() {
        return nombreProveedorAptitud;
    }

    public void setNombreProveedorAptitud(String nombreProveedorAptitud) {
        this.nombreProveedorAptitud = nombreProveedorAptitud;
    }

    public String getCuitProveedorAptitud() {
        return cuitProveedorAptitud;
    }

    public void setCuitProveedorAptitud(String cuitProveedorAptitud) {
        this.cuitProveedorAptitud = cuitProveedorAptitud;
    }

    public String getLocalidadProveedorAptitud() {
        return localidadProveedorAptitud;
    }

    public void setLocalidadProveedorAptitud(String localidadProveedorAptitud) {
        this.localidadProveedorAptitud = localidadProveedorAptitud;
    }

    public List<MedicosProveedor> getMedicosProveedor() {
        return medicosProveedor;
    }

    public void setMedicosProveedor(List<MedicosProveedor> medicosProveedor) {
        this.medicosProveedor = medicosProveedor;
    }

  
    
    
    
}
