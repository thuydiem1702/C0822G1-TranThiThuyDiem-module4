package com.casestudy.case_study.service.security.user;

import com.casestudy.case_study.model.security.User;
import com.casestudy.case_study.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
