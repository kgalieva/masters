package dao.impl;

import dao.CategoryDao;
import model.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CateroryDaoImpl extends CRUDDaoImpl<Category> implements CategoryDao {

    @Autowired
    public CateroryDaoImpl(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}
