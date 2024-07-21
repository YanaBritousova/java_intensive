package org.example.service;

import org.example.models.Records;

import java.util.Date;

public interface RecordService {
    Records get(long recordId);
    void update(long id, Date date);
    void save(Records record);
    void delete(long recordId);
}
