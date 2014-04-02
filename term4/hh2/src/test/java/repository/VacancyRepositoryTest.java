package repository;

import config.DataSourceTestConfig;
import config.PersistenceConfig;
import model.Vacancy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static repository.fixture.TestData.*;
import static repository.fixture.TestConstants.VacancyConstants.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceTestConfig.class, PersistenceConfig.class})
public class VacancyRepositoryTest {

    @Autowired
    private VacancyRepository vacancyRepository;

    @Test
    public void testFindAll() throws SQLException {
        Vacancy newVacancy = standardVacancy();
        vacancyRepository.save(newVacancy);
        Iterable<Vacancy> vacancyList = vacancyRepository.findAll();
        assertNotNull(vacancyList);
        assertTrue(vacancyList.iterator().hasNext());
        for(Vacancy vacancy: vacancyList) {
            assertNotNull(vacancy);
            assertNotNull(vacancy.getId());
        }
    }

    @Test
    public void testCRUD(){
        Vacancy vacancy = standardVacancy();
        vacancyRepository.save(vacancy);
        vacancy = vacancyRepository.findOne(vacancy.getId());
        assertEquals(vacancy.getTitle(), VACANCY_TITLE);
        vacancy.setTitle("Programmer");
        vacancyRepository.save(vacancy);
        assertEquals(vacancyRepository.findOne(vacancy.getId()).getTitle(), "Programmer");
        vacancyRepository.delete(vacancy);
        assertNull(vacancyRepository.findOne(vacancy.getId()));
    }

}
