/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.tcswpractica04;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author carlos
 */
public class DAOEmpleados implements IDAOGeneral<Empleados, Long> {

    @Override
    public boolean guardar(Empleados pojo) {
        Session session = null;
        Transaction t = null;
        try {
            session = HibernateUtils.getSession();
            t = session.beginTransaction();
            session.save(pojo);
            t.commit();
            return true;
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Empleados eliminar(Long id) {
       Session session = HibernateUtils.getSession();
    Transaction t = session.beginTransaction();

    Empleados emp = session.get(Empleados.class, id); // Busca el empleado
    if (emp != null) {
        session.delete(emp); // Lo elimina si existe
    }

    t.commit();
    session.close();

    return emp; // Devuelve el empleado eliminado o null si no existía
    }

    @Override
    public Empleados modificar(Empleados pojo, Long id) {
        Session session = HibernateUtils.getSession();
    Transaction t = session.beginTransaction();

    // obtener el empleado existente
    Empleados existente = session.get(Empleados.class, id);

    if (existente != null) {
        // asignar los nuevos valores con setters
        existente.setNombre(pojo.getNombre());
        existente.setDireccion(pojo.getDireccion());
        existente.setTelefono(pojo.getTelefono());

        // guardar cambios
        session.update(existente);
        t.commit();
    }

    session.close();
    return existente;
    }

    @Override
    public Empleados findByID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Empleados> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
