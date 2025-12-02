package org.uv.tcswpractica04;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "departamento")
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "departamento_clave_seq")
    @SequenceGenerator(name = "departamento_clave_seq",
            sequenceName = "departamento_clave_seq",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "clave")
    private Long clave;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "depto",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Empleados> lstEmpleados;

    // Getters y setters
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
