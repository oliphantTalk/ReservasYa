package com.ttps.reservasya.services;

import com.ttps.reservasya.models.User;
import com.ttps.reservasya.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserDetails findByUserName(String userName) {
        User user = userRepository.findByUserName(userName).get();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoles().stream().map(val -> grantedAuthorities.add(new SimpleGrantedAuthority(val.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
