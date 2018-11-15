package com.ttps.reservasya.services;


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
public class UserServiceImpl implements UserService {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private  UserRepository userRepository;
    private  RoleService roleService;
    private  BCryptPasswordEncoder bCryptPasswordEncoder;

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
        return this.userRepository.findByUsername(userName).orElseThrow(UserNotFoundException::new);
    }

    public User updateUser(User user) { return this.userRepository.save(user); }

    public void deleteUser(User user) {
        this.userRepository.delete(user);
    }

    public User createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            // FIXME esto no se si sirve asi...
            user.setRole(this.roleService.findOne(user.getId()).orElse(new Role()));
        }
        return this.userRepository.save(user);
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
