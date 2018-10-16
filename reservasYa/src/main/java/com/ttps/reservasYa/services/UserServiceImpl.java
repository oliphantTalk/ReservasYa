package com.ttps.reservasYa.services;

import com.ttps.reservasYa.models.User;
import com.ttps.reservasYa.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findOne(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public User updateUser(User user) { return this.userRepository.save(user); }

    @Override
    public void deleteUser(User user) {
        this.userRepository.delete(user);
    }

    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }
}
