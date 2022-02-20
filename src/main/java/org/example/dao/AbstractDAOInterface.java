package org.example.dao;

import java.io.Serializable;
import java.util.List;

public interface AbstractDAOInterface<T extends Serializable> {
    // zapis nowego rekordu do bazy danych
    public void persist(T entity);
    // aktualizacja rekordu
    public void update(T entity);

    public T findById(Integer id);
    // kasowanie
    public void delete(T entity);
    // kasowanie wszystkiego
    public void deleteAll();
    // wyświetlenie wszystkiego
    public List<T> findAll();
}
