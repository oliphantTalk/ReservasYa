package com.ttps.reservasya.services;


import com.ttps.reservasya.exceptions.UserNotFoundException;
import com.ttps.reservasya.models.User;
import com.ttps.reservasya.models.repository.RoleRepository;
import com.ttps.reservasya.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }



    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public BCryptPasswordEncoder getbCryptPasswordEncoder() {
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
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

    public Optional<User> findByEmail(@Email String email){

        return this.userRepository.findByEmail(email);
    }

    public User findByUserName(String userName){
        return this.userRepository.findByUserName(userName).orElseThrow(UserNotFoundException::new);
    }

    public User updateUser(User user) { return this.userRepository.save(user); }

    public void deleteUser(User user) {
        this.userRepository.delete(user);
    }

    public User createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole(this.roleRepository.getOne(1L));
        }
        return this.userRepository.save(user);
    }




}
