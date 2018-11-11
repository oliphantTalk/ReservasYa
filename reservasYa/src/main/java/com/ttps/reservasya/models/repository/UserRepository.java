package com.ttps.reservasya.models.repository;

import com.ttps.reservasya.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from user u where u.email = ?1", nativeQuery = true)
    public Optional<User> findByEmail(@Email String email);

    @Query(value = "select * from user u where u.user_name = ?1", nativeQuery = true)
    public Optional<User> findByUserName(String userName);


}
