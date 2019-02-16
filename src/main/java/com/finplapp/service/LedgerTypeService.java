package com.finplapp.service;

import java.util.List;

public interface LedgerTypeService<T> {

    void saveType(T t);

    T findByType(String type);

    List<T> findAll();

    void deleteType(T t);
}
