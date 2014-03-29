package service.impl;

import model.CV;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CVRepository;
import repository.UserRepository;
import service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private CVRepository cvRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CVRepository cvRepository) {
        this.userRepository = userRepository;
        this.cvRepository = cvRepository;
    }

    @Override
    public CV getCVById(Long id) {
        return cvRepository.findOne(id);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }
}
