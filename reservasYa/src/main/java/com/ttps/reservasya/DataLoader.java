package com.ttps.reservasya;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ttps.reservasya.models.User;
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

    @Autowired
    public DataLoader(UserService userService) {
        this.userService = userService;
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {
        URL url = new URL("file:src/test/resources/users_h2.json");
        List<User> users = CustomObjectMapper.getMapper().readValue(url, new TypeReference<List<User>>() {
        });
        users.forEach(this.userService::createUser);
    }
}


//todo i can also check this first posibility
// https://stackoverflow.com/questions/38040572/spring-boot-loading-initial-data