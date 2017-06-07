package dao;

import entity.Clients;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utilities.HibernateUtil;

import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

/**
 * Created by Sviatoslav on 06.06.2017.
 */
public class ClientsDaoImpl implements IClientsDao {

    private SessionFactory sessionFactory;

    public ClientsDaoImpl() {
        sessionFactory = HibernateUtil.createSession();
    }

    @Override
    public Clients create() {
        Clients clients = null;

        //create session and transaction
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            // getting parameters form JSF page and create new Client;
            String name = getFacesParam().get("name");
            String lastName = getFacesParam().get("last");
            int age = Integer.parseInt(getFacesParam().get("age"));
            String address = getFacesParam().get("address");
            clients = new Clients(name, lastName, age, address);

            //saving our clients in DB
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

            String name = getFacesParam().get("name");
            String lastName = getFacesParam().get("last");
            int age = Integer.parseInt(getFacesParam().get("age"));
            String address = getFacesParam().get("address");

            //update out Client setting new values from forms
            clients.setName(name);
            clients.setAge(age);
            clients.setLastName(lastName);
            clients.setName(address);

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

    public Map<String, String> getFacesParam() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    }
}
