package dao.impl;

import dao.CRUDDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.List;

public class CRUDDaoImpl<T>  implements CRUDDao<T>{

    private Class<T> type;

    private SessionFactory sessionFactory;

    public CRUDDaoImpl(Class<T> type, SessionFactory sessionFactory) {
        this.type = type;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T findById(Long id) {
        return (T)getSession().get(type, id);
    }

    @Override
    public T save(T objectToSave) {
        getSession().saveOrUpdate(objectToSave);
        return objectToSave;
    }

    @Override
    public List<T> findAll() throws SQLException {
        return getSession().createCriteria(type).list();
    }

    @Override
    public void delete(T item) throws SQLException {
        getSession().delete(item);
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
