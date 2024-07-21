package org.example.service.impl;

import org.example.dao.impl.RecordDAOImpl;
import org.example.models.Records;
import org.example.service.RecordService;

import java.util.Date;

public class RecordServiceImpl implements RecordService {
    private final RecordDAOImpl recordDAO;

    public RecordServiceImpl() {
        this.recordDAO = new RecordDAOImpl();
    }

    @Override
    public Records get(long recordId) {
        return recordDAO.get(recordId).orElse(null);
    }

    @Override
    public void update(long id, Date date) {
        recordDAO.update(id,date);
    }

    @Override
    public void save(Records record) {
        recordDAO.save(record);
    }

    @Override
    public void delete(long recordId) {
        recordDAO.delete(recordId);
    }
}
