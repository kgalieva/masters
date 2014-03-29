package service;

import model.CV;
import model.User;

public interface UserService {

    CV getCVById(Long id);

    User getUserById(Long id);
}
