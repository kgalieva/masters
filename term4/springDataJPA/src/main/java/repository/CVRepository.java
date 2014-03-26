package repository;

import model.CV;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CVRepository extends CrudRepository<CV, Long>, CVRepositoryCustom {

    //SELECT * FROM cv WHERE title = ?
    List<CV> findByTitle(String title);

    @Modifying
    @Query("update CV cv set cv.title = ?2 where cv.title = ?1")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    int updateByTitle(String title, String newTitle);


}
