package org.uv.tcswpractica04;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TCSWPractica04 {

    public static void main(String[] args) {
        Logger log = Logger.getLogger(TCSWPractica04.class.getName());
        log.log(Level.INFO, "Hello World");

        try (Session session = HibernateUtils.getSession()) {
            Transaction t = session.beginTransaction();

            Departamentos d1 = session.get(Departamentos.class, 1L);
            if (d1 == null) {
                throw new IllegalStateException("No existe el departamento con clave=1");
            }

            Empleados emp = new Empleados();
            emp.setNombre("Gabrel");
            emp.setDireccion("Av. 11");
            emp.setTelefono("7777777");
            emp.setDepto(d1);

            session.save(emp);
            t.commit();
            log.log(Level.INFO, "Empleado insertado", emp.getClave());
        }
    }
}