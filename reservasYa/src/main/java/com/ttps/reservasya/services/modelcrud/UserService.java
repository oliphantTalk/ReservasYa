package com.ttps.reservasya.services.modelcrud;


import com.ttps.reservasya.exceptions.NoElementInDBException;
import com.ttps.reservasya.exceptions.UserNotFoundException;
import com.ttps.reservasya.models.users.User;
import com.ttps.reservasya.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.util.Optional;

@Service
public class UserService extends BasicCrudService<User, UserRepository> {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private  RoleService roleService;
    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    
    public UserService(UserRepository userRepository){
        super(userRepository);
    }
    
    @Override
    public User createOne(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole(this.roleService.findById(1L).orElseThrow(NoElementInDBException::new));
        }
        return repository.save(user);
    }

    public Optional<User> findByEmail(@Email String email){
        return repository.findByEmail(email);
    }

    public User findByUserName(String userName){
        return repository.findByUsername(userName).orElseThrow(UserNotFoundException::new);
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public BCryptPasswordEncoder getbCryptPasswordEncoder() {
        return bCryptPasswordEncoder;
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
