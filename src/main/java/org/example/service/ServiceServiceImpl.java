package org.example.service;

import org.example.dao.ServiceDAOImpl;
import org.example.models.Service;

public class ServiceServiceImpl implements ServiceService{
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
