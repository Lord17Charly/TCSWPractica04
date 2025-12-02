package org.uv.tcswpractica04;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TCSWPractica04 {

    public static void main(String[] args) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtils.getSession();
            tx = session.beginTransaction();

            // Intentamos obtener un departamento con clave = 1
            Departamento d1 = session.get(Departamento.class, 1L);

            if (d1 == null) {
                d1 = new Departamento();
                d1.setNombre("Sistema");
                session.save(d1); // guarda el departamento nuevo
                // session.flush() no es necesario aquí, commit lo hará
            } else {
                d1.setNombre("Otro");
                session.update(d1);
            }

            // Crear empleado y asociarlo al departamento d1
            Empleados emp = new Empleados();
            emp.setNombre("Juan");
            emp.setDireccion("Av Benustiano Carranza");
            emp.setTelefono("2721674323");
            emp.setDepto(d1);

            session.save(emp); // persistir empleado nuevo

            tx.commit();
            System.out.println("Se guardó correctamente.");

        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
        } finally {
            if (session != null) session.close();
            HibernateUtils.shutdown();
        }
    }
}
