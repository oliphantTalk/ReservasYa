package com.ttps.reservasya.models.repository;
import com.ttps.reservasya.models.Role
import com.ttps.reservasya.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {


}
