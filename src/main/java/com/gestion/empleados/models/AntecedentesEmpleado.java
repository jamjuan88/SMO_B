
package com.gestion.empleados.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="AntecedentesEmpleado")
public class AntecedentesEmpleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAntecedentes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @JoinColumn(name="idAptitudes")
    private Aptitudes aptitudes; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @JoinColumn(name="idProveedorAptitud")
    private ProveedoresAptitudes proveedoresAptitudes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @JoinColumn(name="idMedicosProveedores")
    private MedicosProveedor medicosProveedor;
    
    @ManyToOne
    @JoinColumn(name = "empleadoId")
    private Empleado empleado;

    public Integer getIdAntecedentes() {
        return idAntecedentes;
    }

    public void setIdAntecedentes(Integer idAntecedentes) {
        this.idAntecedentes = idAntecedentes;
    }

    public Aptitudes getAptitudes() {
        return aptitudes;
    }

    public void setAptitudes(Aptitudes aptitudes) {
        this.aptitudes = aptitudes;
    }

    public ProveedoresAptitudes getProveedoresAptitudes() {
        return proveedoresAptitudes;
    }

    public void setProveedoresAptitudes(ProveedoresAptitudes proveedoresAptitudes) {
        this.proveedoresAptitudes = proveedoresAptitudes;
    }

    public MedicosProveedor getMedicosProveedor() {
        return medicosProveedor;
    }

    public void setMedicosProveedor(MedicosProveedor medicosProveedor) {
        this.medicosProveedor = medicosProveedor;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    
    
    
}
