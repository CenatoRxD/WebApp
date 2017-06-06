package utilities;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Sviatoslav on 04.06.2017.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e){
            System.err.println("Initialization failed" + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSession(){
        return sessionFactory;
    }
}
