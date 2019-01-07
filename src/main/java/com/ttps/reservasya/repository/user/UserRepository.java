package com.ttps.reservasya.repository.user;

import com.ttps.reservasya.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(@Email String email);

    Optional<User> findByUsername(String userName);


}
