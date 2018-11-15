package com.ttps.reservasya.repository;

import com.ttps.reservasya.models.users.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
