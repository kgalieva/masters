package service.impl;

import model.CV;
import model.User;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    @Override
    public CV getCVByIdWithCategories(Long id) {
        CV cv = cvRepository.findOne(id);
        Hibernate.initialize(cv.getCategories());
        return cv;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public Iterable<CV> getAllCVs() {
        return cvRepository.findAll();
    }

    @Override
    public Iterable<CV> getCVsByNamePart(String term) {
        return cvRepository.findByTitleStartingWithIgnoreCase(term);
    }

    @Override
    public void saveCV(CV cv) {
        cvRepository.save(cv);
    }

    @Override
    public Iterable<CV> getCVListByCategoryId(Long categoryId) {
        return cvRepository.findByCategory(categoryId);
    }
}
