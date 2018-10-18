package com.ttps.reservasYa.models.repository;

import com.ttps.reservasYa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from user u where u.email = ?1", nativeQuery = true)
    public User findByEmail(@Email String email);

    @Query(value = "select * from user u where u.userName = ?1", nativeQuery = true)
    public User findByUserName(String userName);


}
