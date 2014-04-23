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

    @Transactional
    @Query("select cv from CV cv join cv.categories c where c.id = ?1")
      //      SELECT * FROM cv, cv_category WHERE ... AND cv_category.category_id=?1
    Iterable<CV> findByCategory(Long categoryID);

    List<CV> findByTitleStartingWithIgnoreCase(String title);
}
