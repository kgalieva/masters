package repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import model.CV;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CVRepository extends CrudRepository<CV, Long>, CVRepositoryCustom {
    List<CV> findByTitle(String title);

    @Modifying
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Query("update CV cv set cv.title = ?2 where cv.title LIKE ?1")
    int setNewTitle(String title, String newTitle);
}
