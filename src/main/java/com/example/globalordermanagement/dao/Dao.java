package com.example.globalordermanagement.dao;

import java.util.List;

public interface Dao<T> {
    void create(T t);

    List<T> read();

    void update(T t);

    void delete(int id);
}
