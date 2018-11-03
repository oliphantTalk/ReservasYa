package com.ttps.reservasya.services;


import com.ttps.reservasya.exceptions.UserNotFoundException;
import com.ttps.reservasya.models.Role;
import com.ttps.reservasya.models.User;
import com.ttps.reservasya.models.repository.RoleRepository;
import com.ttps.reservasya.models.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public BCryptPasswordEncoder getbCryptPasswordEncoder() {
        return bCryptPasswordEncoder;
    }

    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public Optional<User> findOne(Long id) {
        return this.userRepository.findById(id);
    }

    public User findByEmail(@Email String email){

        return this.userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public User findByUserName(String userName){
        return this.userRepository.findByUserName(userName).orElseThrow(UserNotFoundException::new);
    }

    public User updateUser(User user) { return this.userRepository.save(user); }

    public void deleteUser(User user) {
        this.userRepository.delete(user);
    }

    public User createUser(User user) {

        user.editPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        return this.userRepository.save(user);
    }




}
