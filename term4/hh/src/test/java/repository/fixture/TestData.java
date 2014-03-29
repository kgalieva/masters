package repository.fixture;

import model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static repository.fixture.TestConstants.CompanyConstants.*;
import static repository.fixture.TestConstants.UserConstants.*;
import static repository.fixture.TestConstants.CVConstants.*;
import static repository.fixture.TestConstants.CategoryConstants.*;
import static repository.fixture.TestConstants.VacancyConstants.*;
import static repository.fixture.TestConstants.InviteConstants.*;

public class TestData {

    public static Category standardCategory() {
        Category category = new Category();
        category.setName(CATEGORY_NAME);
        return category;
    }

    public static List<Category> standardCategoryList() {
        List<Category> categories = new ArrayList<Category>();
        Category category = new Category();
        category.setName("Technology");
        categories.add(standardCategory());
        categories.add(category);
        return categories;
    }

    public static User standardUser() {
        User user = new User();
        user.setGender(Gender.FEMALE);
        user.setName(USER_NAME);
        user.setCity("Казань");
        user.setEmail("alex@gmail.com");
        user.setPassword("qwe");
        user.setSalt("rty");
        return user;
    }

    public static CV standardCV() {
        CV cv = new CV();
        cv.setCategories(standardCategoryList());
        cv.setOwner(standardUser());
        cv.setText("Python, Java, Ruby");
        cv.setTitle(CV_TITLE);
        return cv;
    }

    public static Company standardCompany() {
        Company company = new Company();
        company.setName(COMPANY_NAME);
        company.setCity("Казань");
        company.setEmail("hr@hp.com");
        company.setPassword("123");
        company.setSalt("456");
        List<Vacancy> vacancies = new ArrayList<Vacancy>();
        vacancies.add(standardVacancy());
        company.setVacancies(vacancies);
        return company;
    }

    public static Vacancy standardVacancy(){
        Vacancy vacancy = new Vacancy();
        vacancy.setCity("Казань");
        vacancy.setDescription("Требуется ведущий аналитик");
        vacancy.setSalary(new BigDecimal(12345));
        vacancy.setExperience(5);
        vacancy.setTitle(VACANCY_TITLE);
        return vacancy;
    }

    public static Invite standardInvite() {
        Invite invite = new Invite();
        invite.setType(INVITE_TYPE);
        return invite;
    }

}
