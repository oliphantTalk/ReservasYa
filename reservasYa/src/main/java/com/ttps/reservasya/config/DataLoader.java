package com.ttps.reservasya.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ttps.reservasya.models.users.Role;
import com.ttps.reservasya.models.users.User;
import com.ttps.reservasya.services.modelcrud.RoleService;
import com.ttps.reservasya.services.modelcrud.UserService;
import com.ttps.reservasya.utils.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DataLoader(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<User> users = CustomObjectMapper.getMapper().readValue(new URL("file:src/test/resources/users_h2.json"), new TypeReference<List<User>>() {
        });
        List<Role> roles = CustomObjectMapper.getMapper().readValue(new URL("file:src/test/resources/roles_h2.json"), new TypeReference<List<Role>>() {
        });

        roleService.createAll(roles);
        userService.createAll(users);

    }

}
