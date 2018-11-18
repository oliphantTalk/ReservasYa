package com.ttps.reservasya.services.modelcrud;

import com.ttps.reservasya.models.users.Role;
import com.ttps.reservasya.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BasicCrudService<Role, RoleRepository> {

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        super(roleRepository);
    }


}
