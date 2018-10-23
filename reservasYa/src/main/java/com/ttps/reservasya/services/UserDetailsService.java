package com.ttps.reservasya.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {
    public UserDetails findByUserName(String username);
}
