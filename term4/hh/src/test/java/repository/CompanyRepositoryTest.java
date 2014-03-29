package repository;

import config.DataSourceTestConfig;
import config.PersistenceConfig;
import model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static repository.fixture.TestData.*;
import static repository.fixture.TestConstants.CompanyConstants.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceTestConfig.class, PersistenceConfig.class})
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void testFindAll() throws SQLException {
        companyRepository.save(standardCompany());
        Iterable<Company> companyList = companyRepository.findAll();
        assertNotNull(companyList);
        assertTrue(companyList.iterator().hasNext());
        for(Company company: companyList) {
            assertNotNull(company);
            assertNotNull(company.getId());
        }
    }

    @Test
    public void testCRUD(){
        Company company = standardCompany();
        companyRepository.save(company);
        company = companyRepository.findOne(company.getId());
        assertEquals(company.getName(), COMPANY_NAME);
        company.setName("Apple");
        companyRepository.save(company);
        assertEquals(companyRepository.findOne(company.getId()).getName(), "Apple");
        companyRepository.delete(company);
        assertNull(companyRepository.findOne(company.getId()));
    }
}
