package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;

@Repository
public class CVRepositoryImpl implements CVRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Long getIdsByTitle(String title) {
        return ((BigInteger)entityManager.createNativeQuery("SELECT id FROM cv WHERE title=?1").
                setParameter(1, title).getSingleResult()).longValue();
    }
}
