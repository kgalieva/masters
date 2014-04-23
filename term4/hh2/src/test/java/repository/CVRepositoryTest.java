package repository;

import config.DataSourceTestConfig;
import config.PersistenceConfig;
import model.CV;
import model.Category;
import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;
import static repository.fixture.TestData.*;
import static repository.fixture.TestConstants.CVConstants.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceTestConfig.class, PersistenceConfig.class})
public class CVRepositoryTest {

    @Autowired
    private CVRepository cvRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private  InviteRepository inviteRepository;

    private CV createStandardCV() {
        CV newCV = standardCV();
        User user = userRepository.save(newCV.getOwner());
        newCV.setOwner(user);
        newCV.setCategories((List<Category>) categoryRepository.findAll());
        return cvRepository.save(newCV);
    }

    @Test
    public void testFindAll() throws SQLException {
        createStandardCV();
        Iterable<CV> cvList = cvRepository.findAll();
        assertNotNull(cvList);
        assertTrue(cvList.iterator().hasNext());
        for(CV cv: cvList) {
            assertNotNull(cv);
            assertNotNull(cv.getId());
        }
        cvRepository.deleteAll();
    }

    @Test
    public void testFindByTitle() throws SQLException {
        CV newCV = createStandardCV();

        List<CV> cvList = cvRepository.findByTitle(newCV.getTitle());
        assertNotNull(cvList);
        assertFalse(cvList.isEmpty());
        for(CV cv: cvList) {
            assertNotNull(cv);
            assertNotNull(cv.getId());
        }
    }

    @Test
    public void testFindByTitleStartingWith() throws SQLException {
        CV newCV = createStandardCV();
        String term = newCV.getTitle().substring(0,2);
        List<CV> cvList = cvRepository.findByTitleStartingWithIgnoreCase(term.toLowerCase());
        assertNotNull(cvList);
        assertFalse(cvList.isEmpty());
        for(CV cv: cvList) {
            assertNotNull(cv);
            assertNotNull(cv.getId());
            assertTrue(cv.getTitle().startsWith(term));
        }

        cvList = cvRepository.findByTitleStartingWithIgnoreCase(term.toUpperCase());
        assertNotNull(cvList);
        assertFalse(cvList.isEmpty());
        for(CV cv: cvList) {
            assertNotNull(cv);
            assertNotNull(cv.getId());
            assertTrue(cv.getTitle().startsWith(term));
        }
    }

    @Test
    public void testCRUD(){
        CV cv = createStandardCV();
        cv = cvRepository.findOne(cv.getId());
        assertEquals(cv.getTitle(), CV_TITLE);
        cv.setTitle("Programmer");
        cvRepository.save(cv);
        assertEquals(cvRepository.findOne(cv.getId()).getTitle(), "Programmer");
        //delete
        cvRepository.delete(cv);
        assertNull(cvRepository.findOne(cv.getId()));
        cvRepository.deleteAll();
    }

    @Test
    public void testSetNewTitle(){
        CV cv = createStandardCV();
        cv = cvRepository.findOne(cv.getId());
        assertEquals(cv.getTitle(), CV_TITLE);
        int res = cvRepository.setNewTitle(CV_TITLE,"Programmer");
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
        inviteRepository.deleteAll();
        cvRepository.deleteAll();
        CV cv = createStandardCV();
        assertEquals(cvRepository.findId(CV_TITLE), cv.getId());
    }

    @Test
    public void testFindByCategory() {
        CV cv = createStandardCV();
        Iterable<CV> cvs = cvRepository.findByCategory(cv.getCategories().get(0).getId());
        assertNotNull(cvs);
        assertTrue(cvs.iterator().hasNext());
        assertNotNull(cvs.iterator().next());
    }
}