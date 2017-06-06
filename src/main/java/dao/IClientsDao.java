package dao;

import entity.Clients;

import java.util.List;

/**
 * Created by Sviatoslav on 04.06.2017.
 */
public interface IClientsDao {

    Clients create();

    Clients read(int index);

    Clients update(int index);

    Clients delete(int index);

    List<Clients> readAll();
}
