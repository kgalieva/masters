package repository;

import config.PersistenceTestConfig;
import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static repository.fixture.TestConstants.UserConstants.USER_NAME;
import static repository.fixture.TestData.standardUser;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceTestConfig.class})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindAll() throws SQLException {
        User newUser = standardUser();
        userRepository.save(newUser);
        Iterable<User> userList = userRepository.findAll();
        assertNotNull(userList);
        assertTrue(userList.iterator().hasNext());
        for(User user: userList) {
            assertNotNull(user);
            assertNotNull(user.getId());
        }
        userRepository.deleteAll();
    }

    @Test
    public void testCRUD(){
        User user = standardUser();
        userRepository.save(user);
        user = userRepository.findOne(user.getId());
        assertEquals(user.getName(), USER_NAME);
        assertEquals(user.getId(), standardUser().getId());
        user.setName("Саша");
        userRepository.save(user);
        assertEquals(userRepository.findOne(user.getId()).getName(), "Саша");
        userRepository.delete(user);
        assertNull(userRepository.findOne(user.getId()));
    }

}
