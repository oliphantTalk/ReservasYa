package com.ttps.reservasya.services.user;

import com.ttps.reservasya.repository.user.RoleRepository;
import com.ttps.reservasya.models.user.role.Role;
import com.ttps.reservasya.services.BasicCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BasicCrudService<Role, RoleRepository> {

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        super(roleRepository);
    }


}
