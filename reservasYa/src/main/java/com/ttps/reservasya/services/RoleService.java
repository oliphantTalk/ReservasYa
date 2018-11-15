package com.ttps.reservasya.services;

import com.ttps.reservasya.models.users.Role;
import com.ttps.reservasya.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements BasicCrudService<Role> {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findOne(Long id) {
        return roleRepository.findById(id);
    }

    public Optional<List<Role>> findAll() {
        return Optional.of(roleRepository.findAll());
    }

    public Role createOne(Role role) {
        return roleRepository.save(role);
    }

    public Role updateOne(Role role) {
        return roleRepository.save(role);
    }

    public void deleteOne(Long id) {
        roleRepository.deleteById(id);
    }
}
