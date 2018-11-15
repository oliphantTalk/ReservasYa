package com.ttps.reservasya.services;


import com.ttps.reservasya.exceptions.NoElementInDBException;
import com.ttps.reservasya.exceptions.UserNotFoundException;
import com.ttps.reservasya.models.users.Role;
import com.ttps.reservasya.models.users.User;
import com.ttps.reservasya.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements BasicCrudService<User> {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private  UserRepository userRepository;
    private  RoleService roleService;
    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    public Optional<User> findOne(Long id) {
        return this.userRepository.findById(id);
    }

    public Optional<List<User>> findAll() {
        return Optional.of(this.userRepository.findAll());
    }

    public User createOne(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole(this.roleService.findOne(1L).orElseThrow(NoElementInDBException::new));
        }
        return this.userRepository.save(user);
    }

    public User updateOne(User user) { return this.userRepository.save(user); }

    public void deleteOne(Long id) {
        this.userRepository.deleteById(id);
    }

    public Optional<User> findByEmail(@Email String email){
        return this.userRepository.findByEmail(email);
    }

    public User findByUserName(String userName){
        return this.userRepository.findByUsername(userName).orElseThrow(UserNotFoundException::new);
    }


    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public BCryptPasswordEncoder getbCryptPasswordEncoder() {
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}
