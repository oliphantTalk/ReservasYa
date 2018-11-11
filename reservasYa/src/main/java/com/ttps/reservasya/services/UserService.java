package com.ttps.reservasya.services;

import com.ttps.reservasya.models.User;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<User> findOne(Long id);
    public List<User> findAll();
    public User updateUser(User user);
    public void deleteUser(User user);
    public User createUser(User user);
    public Optional<User> findByEmail(@Email String email);
    public User findByUserName(String userName);

    //todo los findBy* debieran devolver Optional

}
