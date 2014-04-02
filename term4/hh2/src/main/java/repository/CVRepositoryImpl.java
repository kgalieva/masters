package repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;

@Repository
public class CVRepositoryImpl implements CVRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long findId(String title) {
        Query query = entityManager.createNativeQuery("SELECT id from CV WHERE title = ?1");
        query.setParameter(1, title);
        return ((BigInteger)query.getSingleResult()).longValue();
    }
}
