package com.ttps.reservasya.repository;

import com.ttps.reservasya.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(@Email String email);

    public Optional<User> findByUsername(String userName);


}
