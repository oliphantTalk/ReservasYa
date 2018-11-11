package com.ttps.reservasya;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ttps.reservasya.models.Role;
import com.ttps.reservasya.models.User;
import com.ttps.reservasya.models.repository.RoleRepository;
import com.ttps.reservasya.services.UserService;
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
    private final RoleRepository roleRepository;

    @Autowired
    public DataLoader(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<User> users = CustomObjectMapper.getMapper().readValue(new URL("file:src/test/resources/users_h2.json"), new TypeReference<List<User>>() {
        });
        List<Role> roles = CustomObjectMapper.getMapper().readValue(new URL("file:src/test/resources/roles_h2.json"), new TypeReference<List<Role>>() {
        });
        roles.forEach(this.roleRepository::save);
        users.forEach(this.userService::createUser);

    }

}
