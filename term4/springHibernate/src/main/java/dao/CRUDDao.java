package dao;

import java.sql.SQLException;
import java.util.List;

public interface CRUDDao<T> {
    public T save(T item) throws SQLException;
    public T findById(Long id) throws SQLException;
    public List<T> findAll() throws SQLException;
    public void delete(T item) throws SQLException;
}
