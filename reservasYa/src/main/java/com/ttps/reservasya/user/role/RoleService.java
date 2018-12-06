package com.ttps.reservasya.user.role;

import com.ttps.reservasya.services.modelcrud.BasicCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BasicCrudService<Role, RoleRepository> {

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        super(roleRepository);
    }


}
