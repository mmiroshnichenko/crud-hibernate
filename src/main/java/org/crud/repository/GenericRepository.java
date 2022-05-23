package org.crud.repository;

import java.io.IOException;
import java.util.List;

public interface GenericRepository<T, ID> {
    T getById(ID id);
    List<T> getAll();
    T save(T item);
    T update(T item);
    void deleteById(ID id);
}
