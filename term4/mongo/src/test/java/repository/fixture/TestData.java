package repository.fixture;

import model.User;

import java.math.BigInteger;

import static repository.fixture.TestConstants.UserConstants.USER_CITY;
import static repository.fixture.TestConstants.UserConstants.USER_NAME;

public class TestData {

    public static User standardUser() {
        User user = new User();
        user.setId(new BigInteger("1"));
        user.setName(USER_NAME);
        user.setCity(USER_CITY);
        user.setEmail("alex@gmail.com");
        user.setPassword("qwe");
        user.setSalt("rty");
        return user;
    }

}
