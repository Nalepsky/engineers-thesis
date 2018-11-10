package com.nalepka.service;

import java.util.List;

public interface GenericService <T> {
    List<T> getAll();
    T getById(Long id);
    T update(T t);
    void delete(Long id);
}
