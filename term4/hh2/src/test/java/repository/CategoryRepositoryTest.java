package repository;

import config.DataSourceTestConfig;
import config.PersistenceConfig;
import model.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static repository.fixture.TestData.*;
import static repository.fixture.TestConstants.CategoryConstants.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceTestConfig.class, PersistenceConfig.class})
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testFindAll() throws SQLException {
        Category newCategory = standardCategory();
        categoryRepository.save(newCategory);
        Iterable<Category> categoryList = categoryRepository.findAll();
        assertNotNull(categoryList);
        assertTrue(categoryList.iterator().hasNext());
        for(Category category: categoryList) {
            assertNotNull(category);
            assertNotNull(category.getId());
        }
    }

    @Test
    public void testCRUD(){
        Category category = standardCategory();
        categoryRepository.save(category);
        category = categoryRepository.findOne(category.getId());
        assertEquals(category.getName(), CATEGORY_NAME);
        category.setName("Sales");
        categoryRepository.save(category);
        assertEquals(categoryRepository.findOne(category.getId()).getName(), "Sales");
        categoryRepository.delete(category);
        assertNull(categoryRepository.findOne(category.getId()));
    }

}
