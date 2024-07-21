package org.example.dao;

import java.util.Optional;

public interface ServiceDAO <T, K>{
    Optional<T> get(K id);

    void save(T t);

    void delete(K id);
}
