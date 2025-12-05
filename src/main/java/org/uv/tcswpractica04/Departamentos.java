/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.tcswpractica04;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author carlos
 */
@Entity
@Table(name = "departamentos")
public class Departamentos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departamentos_clave_seq")
    @SequenceGenerator(
            name = "departamentos_clave_seq",
            sequenceName = "departamentos_clave_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "clave")
    private Long clave;

    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;

    @OneToMany(
            mappedBy = "depto",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Empleados> lstEmpleados = new ArrayList<>();

    public void addEmpleado(Empleados e) {
        lstEmpleados.add(e);
        e.setDepto(this);
    }

    public void removeEmpleado(Empleados e) {
        lstEmpleados.remove(e);
        e.setDepto(null);
    }

    public Long getClave() {
        return clave;
    }

    public void setClave(Long clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Empleados> getLstEmpleados() {
        return lstEmpleados;
    }

    public void setLstEmpleados(List<Empleados> lstEmpleados) {
        this.lstEmpleados = lstEmpleados;
    }
}
