package org.example.models;

public class Service {
    private long id;
    private String service;

    public Service() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setService(String service) {
        this.service = service;
    }

    public long getId() {
        return id;
    }

    public String getService() {
        return service;
    }
}
