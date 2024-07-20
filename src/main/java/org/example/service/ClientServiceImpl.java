package org.example.service;

import org.example.dao.ClientDAOImpl;
import org.example.models.Client;


public class ClientServiceImpl implements ClientService{

    private final ClientDAOImpl clientDAO;

    public ClientServiceImpl() {
        this.clientDAO = new ClientDAOImpl();
    }

    @Override
    public Client get(long clientId) {
        return clientDAO.get(clientId).orElse(null);
    }

    @Override
    public void save(Client client) {
        clientDAO.save(client);
    }

    @Override
    public void delete(long clientId) {
        clientDAO.delete(clientId);
    }
}
