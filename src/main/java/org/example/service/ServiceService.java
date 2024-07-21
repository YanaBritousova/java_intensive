package org.example.service;

import org.example.models.Client;
import org.example.models.Service;

public interface ServiceService {
    Service get(long serviceId);
    void save(Service service);
    void delete(long serviceId);
}
