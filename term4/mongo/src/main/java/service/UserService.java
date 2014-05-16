package service;

import model.User;

import java.math.BigInteger;

public interface UserService {

    User getUserById(BigInteger id);
}
