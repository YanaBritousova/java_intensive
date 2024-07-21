package org.example.service.impl;

import org.example.dao.impl.ServiceDAOImpl;
import org.example.models.Service;
import org.example.service.ServiceService;

public class ServiceServiceImpl implements ServiceService {
    private final ServiceDAOImpl serviceDAO;

    public ServiceServiceImpl() {
        this.serviceDAO = new ServiceDAOImpl();
    }

    @Override
    public Service get(long serviceId) {
        return serviceDAO.get(serviceId).orElse(null);
    }

    @Override
    public void save(Service service) {
        serviceDAO.save(service);
    }

    @Override
    public void delete(long serviceId) {
        serviceDAO.delete(serviceId);
    }
}
