package org.example.service;

import org.example.models.Client;


public interface ClientService {
    Client get(long clientId);
    void save(Client client);
    void delete(long clientId);
}
