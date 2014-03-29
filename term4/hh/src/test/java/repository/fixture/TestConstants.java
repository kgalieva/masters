package repository.fixture;

import model.InviteType;

public interface TestConstants {
    interface CVConstants {
        String CV_TITLE = "Software developer";
    }
    interface CompanyConstants {
        String COMPANY_NAME = "HP";
    }
    interface UserConstants {
        String USER_NAME = "Александра";
    }
    interface VacancyConstants {
        String VACANCY_TITLE = "Аналитик отдела продаж";
    }
    interface CategoryConstants {
        String CATEGORY_NAME = "Logistics";
    }
    interface InviteConstants {
        InviteType INVITE_TYPE = InviteType.RESPONSE;
    }
}
