package org.uv.tcswpractica04;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "empleados2")
public class Empleados {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empleados2_clave_seq")
    @SequenceGenerator(
            name = "empleados2_clave_seq",
            sequenceName = "empleados2_clave_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "clave")
    private Long clave;

    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;

    @Column(name = "direccion", length = 200)
    private String direccion;

    @Column(name = "telefono", length = 40)
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "depto_clave",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_empleados2_depto")
    )
    private Departamentos depto;

    public Empleados() {
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Departamentos getDepto() {
        return depto;
    }

    public void setDepto(Departamentos depto) {
        this.depto = depto;
    }
}