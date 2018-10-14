package com.ttps.reservasYa.services;

import com.ttps.reservasYa.models.User;
import com.ttps.reservasYa.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

    public User getUser(Long id);
    public User updateUser(User user);
    public User deleteUser(User user);
    public User createUser(User user);

}
