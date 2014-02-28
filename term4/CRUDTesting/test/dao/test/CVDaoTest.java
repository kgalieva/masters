package dao.test;

import dao.CVDao;
import dao.Factory;
import model.CV;
import model.Category;
import model.Gender;
import model.User;
import org.junit.Test;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;


public class CVDaoTest {

    private CVDao cvDao = Factory.getInstance().getCvDao();

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
    @Transactional
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
    @Transactional
    public void testCRUD() throws SQLException {
        CV cv = createCV();
        cvDao.add(cv);
        assertEquals(cvDao.findById(cv.getId()).getTitle(), "Software developer");
        cv.setTitle("Programmer");
        cvDao.update(cv);
        assertEquals(cvDao.findById(cv.getId()).getTitle(), "Programmer");
        cvDao.delete(cv);
        assertNull(cvDao.findById(cv.getId()));
    }
}
