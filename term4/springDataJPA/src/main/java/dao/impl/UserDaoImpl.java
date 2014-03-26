package dao.impl;

import dao.UserDao;
import model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends CRUDDaoImpl<User> implements UserDao {
    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        super(User.class, sessionFactory);
    }
}
