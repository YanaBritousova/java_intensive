package org.example.dao;

import java.util.Date;
import java.util.Optional;

public interface RecordDAO<T,K> {
    Optional<T> get(K id);

    void update(K id, Date date);

    void save(T t);

    void delete(K id);
}
