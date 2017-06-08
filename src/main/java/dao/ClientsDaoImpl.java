package dao;

import entity.Clients;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utilities.HibernateUtil;

import java.util.List;

/**
 * Created by Sviatoslav on 06.06.2017.
 */
public class ClientsDaoImpl implements IClientsDao {

    private SessionFactory sessionFactory;

    public ClientsDaoImpl() {
        sessionFactory = HibernateUtil.createSession();
    }

    @Override
    public Clients create(Clients clients) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(clients);
        session.getTransaction().commit();
        session.close();
        return clients;
    }

    @Override
    public Clients read(int index) {

        Session session = sessionFactory.getCurrentSession();
        Clients clients = session.load(Clients.class, index);
        session.close();

        return clients;
    }

    @Override
    public Clients update(int index) {
        Clients clients = null;

        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            clients = session.load(Clients.class, index);
            session.save(clients);
            tx.commit();
        } catch (Throwable th) {
            tx.rollback();
            th.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }

        return clients;
    }

    @Override
    public Clients delete(int index) {
        Clients clients = null;
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            clients = session.load(Clients.class, index);
            session.delete(clients);
            tx.commit();
        } catch (Throwable th) {
            tx.rollback();
            th.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return clients;
    }

    @Override
    public List<Clients> readAll() {
        return sessionFactory.getCurrentSession().createCriteria(Clients.class).list();
    }

}
