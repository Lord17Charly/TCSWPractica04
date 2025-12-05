package org.uv.tcswpractica04;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private HibernateUtils() {
    }

    private static final SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(HibernateUtils.class.getName());

    static {
        try {
            Configuration cfg = new Configuration().configure();
            cfg.addAnnotatedClass(Empleados.class);
            cfg.addAnnotatedClass(Departamentos.class);
            sessionFactory = cfg.buildSessionFactory();
            logger.log(Level.INFO, "SessionFactory creada correctamente");
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void shutdown() {
        logger.log(Level.INFO, "Cerrando SessionFactory");
        getSessionFactory().close();
    }
}