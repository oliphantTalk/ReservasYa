package com.ttps.reservasya.user;


import com.ttps.reservasya.exceptions.NoElementInDBException;
import com.ttps.reservasya.exceptions.UserNotFoundException;
import com.ttps.reservasya.repository.UserRepository;
import com.ttps.reservasya.services.modelcrud.BasicCrudService;
import com.ttps.reservasya.services.modelcrud.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Email;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService extends BasicCrudService<User, UserRepository> implements UserDetailsService {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private RoleService roleService;
    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    
    public UserService(UserRepository userRepository){
        super(userRepository);
    }


    public void signin(User user) {
        SecurityContextHolder.getContext().setAuthentication(authenticate(user));
    }

    private Authentication authenticate(User user) {
        return new UsernamePasswordAuthenticationToken(createOne(user), null, Collections.singleton(createAuthority(user)));
    }


    @Override
    @Transactional
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

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = repository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    private GrantedAuthority createAuthority(User user) {
        return new SimpleGrantedAuthority(user.getRole().getName());
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
