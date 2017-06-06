package beans;

import dao.ClientsDaoImpl;
import dao.IClientsDao;
import entity.Clients;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by Sviatoslav on 05.06.2017.
 */
@ManagedBean(name = "clientsBean")
@SessionScoped
public class ClientsBean extends Clients {

    private IClientsDao clientsDao = new ClientsDaoImpl();

    public IClientsDao getClientsDao() {
        return clientsDao;
    }

    public void setClientsDao(IClientsDao clientsDao) {
        this.clientsDao = clientsDao;
    }
}
