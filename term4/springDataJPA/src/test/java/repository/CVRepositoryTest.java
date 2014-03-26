package repository;

import config.DataSourceConfig;
import config.PersistenceConfig;
import config.SpringDataJPAConfig;
import dao.CVDao;
import model.CV;
import model.Category;
import model.Gender;
import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        DataSourceConfig.class, SpringDataJPAConfig.class})
public class CVRepositoryTest {

    @Autowired
    private CVRepository cvRepository;

    private CV createCV() {
        CV cv = new CV();
        Category category = new Category();
        category.setName("Technology");
        List<Category> categories = new ArrayList<Category>();
        categories.add(category);
        cv.setCategory(categories);
        //cv.setCategory(Collections.singletonList(category));
        User user = new User();
        user.setGender(Gender.FEMALE);
        user.setName("Ann");
        cv.setOwner(user);
        cv.setText("Python, Java, Ruby");
        cv.setTitle("Software developer");
        return cv;
    }

    @Test
    public void testFindAll() throws SQLException {
        Iterable<CV> cvList = cvRepository.findAll();
        assertNotNull(cvList);
        assertFalse(cvList.iterator().hasNext());
        for(CV cv: cvList) {
            assertNotNull(cv);
            assertNotNull(cv.getId());
        }
    }

    @Test
    public void testFindByTitle() throws SQLException {
        CV newCV = createCV();
        //create
        cvRepository.save(newCV);

        List<CV> cvList = cvRepository.findByTitle(newCV.getTitle());
        assertNotNull(cvList);
        assertFalse(cvList.isEmpty());
        assertTrue(cvList.contains(newCV));
        for(CV cv: cvList) {
            assertNotNull(cv);
            assertNotNull(cv.getId());
        }
    }

    @Test
    public void testCRUD(){
        CV cv = createCV();
        cvRepository.save(cv);
        cv = cvRepository.findOne(cv.getId());
        assertEquals(cv.getTitle(), "Software developer");
        cv.setTitle("Programmer");
        cvRepository.save(cv);
        assertEquals(cvRepository.findOne(cv.getId()).getTitle(), "Programmer");
        //delete
        cvRepository.delete(cv);
        assertNull(cvRepository.findOne(cv.getId()));
    }

    @Test
    public void testSetNewTitle(){
        CV cv = createCV();
        cvRepository.save(cv);
        cv = cvRepository.findOne(cv.getId());
        assertEquals(cv.getTitle(), "Software developer");
        int res = cvRepository.updateByTitle("Software developer","Programmer");
        System.out.println("rows = " + res);
        for(CV cv1: cvRepository.findAll()) {
            System.out.println(cv1);
        }
        assertEquals(cvRepository.findOne(cv.getId()).getTitle(), "Programmer");
        //delete
        cvRepository.delete(cv);
        assertNull(cvRepository.findOne(cv.getId()));
    }

    @Test
    public void testFindId(){
        cvRepository.deleteAll();
        CV cv = createCV();
        cvRepository.save(cv);
        assertEquals(cvRepository.getIdsByTitle("Software developer"), cv.getId());
    }
}
