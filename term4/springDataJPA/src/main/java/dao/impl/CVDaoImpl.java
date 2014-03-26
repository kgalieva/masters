package dao.impl;

import dao.CVDao;
import model.CV;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CVDaoImpl extends CRUDDaoImpl<CV> implements CVDao {

    @Autowired
    public CVDaoImpl(SessionFactory sessionFactory) {
        super(CV.class, sessionFactory);
    }

    @Override
    public List<CV> findCVsByOwner(Long owner) {
        return getSession().createQuery("from CV cv where cv.owner.id=?")
                .setParameter(0, owner).list();
    }
}
