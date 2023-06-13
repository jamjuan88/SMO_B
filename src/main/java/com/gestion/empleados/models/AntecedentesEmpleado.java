
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
@Table(name="AntecedentesEmpleado")
public class AntecedentesEmpleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAntecedentes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @JoinColumn(name="idAptitudes")
    private Aptitudes aptitudes; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @JoinColumn(name="idTipoPreexistencia")
    private TipoPreexistencia tipoPreexistencia;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @JoinColumn(name="idPreexistencia")
    private Preexistencias preexistencias;
    
    @Column(name = "descripcionPreexistencia")
    private String descripcionPreexistencia;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @JoinColumn(name="idProveedorAptitud")
    private ProveedoresAptitudes proveedoresAptitudes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @JoinColumn(name="idMedicosProveedores")
    private MedicosProveedor medicosProveedor;
    
    @Column(name = "urlArhivoExamen")
    private String urlArchivoExamen;
    
    @ManyToOne
    @JoinColumn(name = "empleadoId")
    private Empleado empleado;

    public Long getIdAntecedentes() {
        return idAntecedentes;
    }

    public void setIdAntecedentes(Long idAntecedentes) {
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

    public TipoPreexistencia getTipoPreexistencia() {
        return tipoPreexistencia;
    }

    public void setTipoPreexistencia(TipoPreexistencia tipoPreexistencia) {
        this.tipoPreexistencia = tipoPreexistencia;
    }

    public Preexistencias getPreexistencias() {
        return preexistencias;
    }

    public void setPreexistencias(Preexistencias preexistencias) {
        this.preexistencias = preexistencias;
    }

    public String getDescripcionPreexistencia() {
        return descripcionPreexistencia;
    }

    public void setDescripcionPreexistencia(String descripcionPreexistencia) {
        this.descripcionPreexistencia = descripcionPreexistencia;
    }

    public String getUrlArchivoExamen() {
        return urlArchivoExamen;
    }

    public void setUrlArchivoExamen(String urlArchivoExamen) {
        this.urlArchivoExamen = urlArchivoExamen;
    }
    
    
    
    
}
