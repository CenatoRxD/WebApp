package beans;

import dao.ClientsDaoImpl;
import dao.IClientsDao;
import entity.Clients;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sviatoslav on 05.06.2017.
 */
@ManagedBean(name = "clientsBean", eager = true)
@RequestScoped
public class ClientsBean {
    private IClientsDao dao = new ClientsDaoImpl();

    private int id;
    private String name;
    private String lastName;
    private int age;
    private String address;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addClients() {
        Clients clients = new Clients(getName(), getLastName(), getAge(), getAddress());
        dao.create(clients);
    }

    public void readClient() {
        dao.read(getId());
    }

    public void updateClient() {
        dao.update(getId());
    }

    public void deleteClient() {
        dao.delete(getId());
    }

    public List<Clients> showAllClients() {
        return new ArrayList<>(dao.readAll());
    }
}
