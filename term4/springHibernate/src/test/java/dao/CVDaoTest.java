package dao;

import config.PersistenceConfig;
import model.CV;
import model.Category;
import model.Gender;
import model.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;


@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        PersistenceConfig.class})
public class CVDaoTest {

    @Autowired
    private CVDao cvDao;

    private CV createCV() {
        CV cv = new CV();
        Category category = new Category();
        category.setName("Technology");
        cv.setCategory(Collections.singletonList(category));
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
        List<CV> cvList = cvDao.findAll();
        assertNotNull(cvList);
        assertFalse(cvList.isEmpty());
        for(CV cv: cvList) {
            assertNotNull(cv);
            assertNotNull(cv.getId());
        }
    }

    @Test
    public void testFindByOwner() throws SQLException {
        CV newCV = createCV();
        //create
        cvDao.save(newCV);

        List<CV> cvList = cvDao.findCVsByOwner(newCV.getOwner().getId());
        assertNotNull(cvList);
        assertFalse(cvList.isEmpty());
        for(CV cv: cvList) {
            assertNotNull(cv);
            assertNotNull(cv.getId());
        }
    }

    @Test
    public void testCRUD() throws SQLException {
        CV cv = createCV();
        //create
        cvDao.save(cv);
        assertEquals(cvDao.findById(cv.getId()).getTitle(), "Software developer");
        cv.setTitle("Programmer");
        //update
        cvDao.save(cv);
        assertEquals(cvDao.findById(cv.getId()).getTitle(), "Programmer");
        //delete
        cvDao.delete(cv);
        assertNull(cvDao.findById(cv.getId()));
    }
}
