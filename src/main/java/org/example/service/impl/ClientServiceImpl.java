package org.example.service.impl;

import org.example.dao.impl.ClientDAOImpl;
import org.example.models.Client;
import org.example.service.ClientService;


public class ClientServiceImpl implements ClientService {

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
