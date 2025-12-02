/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.tcswpractica04;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
/**
 *
 * @author carlos
 */
public class DAODepartamentos {
    
    public boolean guardar(Departamento depto) {
        Session session = HibernateUtils.getSession();
        Transaction t = session.beginTransaction();
        session.save(depto);
        t.commit();
        session.close();
        return true;
    }

    public Departamento findByID(Long id) {
        Session session = HibernateUtils.getSession();
        Departamento d = session.get(Departamento.class, id);
        session.close();
        return d;
    }

    public List<Departamento> findAll() {
        Session session = HibernateUtils.getSession();
        Query<Departamento> q = session.createQuery("from Departamento", Departamento.class);
        List<Departamento> list = q.list();
        session.close();
        return list;
    }

    // lista empleados de un departamento por id
    public List<Empleados> getEmpleadosByDepartamento(Long departamentoId) {
        Session session = HibernateUtils.getSession();
        Query<Empleados> q = session.createQuery(
            "FROM Empleados e WHERE e.departamento.clave = :deptId", Empleados.class);
        q.setParameter("deptId", departamentoId);
        List<Empleados> list = q.list();
        session.close();
        return list;
    }
    public Departamento eliminar(Long id) {
        Transaction tx = null;
        Departamento dept = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            dept = session.get(Departamento.class, id);
            if (dept != null) {
                session.delete(dept);
            }

            tx.commit();
            return dept;  // si se eliminó, devuelve el objeto; si no, null
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        }
    }
}



