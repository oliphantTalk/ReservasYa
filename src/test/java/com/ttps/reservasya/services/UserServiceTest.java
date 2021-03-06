package com.ttps.reservasya.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttps.reservasya.models.user.User;
import com.ttps.reservasya.services.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest  {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);
    @Autowired
    public ObjectMapper jsonMapper;
    @Autowired
    public UserService userService;
    private static Boolean setUpIsDone = false;

    @Before
    public void populateH2() throws IOException {
        if(!setUpIsDone) {
            URL url = new URL("file:src/test/resources/users_h2.json");
            List<User> users = jsonMapper.readValue(url, new TypeReference<List<User>>() {
            });
            users.forEach(val -> {if(!this.userService.findByEmail(val.getEmail()).isPresent()) this.userService.createOne(val);});
            setUpIsDone = true;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void userServiceTest_ValidationOnCreateFails(){
        User userBadEmail = new User("nano", "asdf", "asdfoj", "fdsjio");
        this.userService.createOne(userBadEmail);
    }

    @Test(expected = TransactionSystemException.class)
    public void userServiceTest_ValidationOnUpdateFails(){
        User userWillFail = this.userService.findById(5L).get();
        userWillFail.setEmail("bad_email");
        this.userService.updateOne(userWillFail);
    }

    @Test
    public void userServiceTest_newOne(){
        User user = createUser();
        updateUser(user);
        deleteUser(user);
    }

    @Test
    public void userServiceTest_findAll() {
        assertThat("Se espera que haiga 5 usuarios en la base", this.userService.findAll().size(), equalTo(5));
        this.userService.findAll().forEach(this::userEquals);
    }

    @Test
    public void userServiceTest_findByEmail(){
        User userByEmail = this.userService.findByEmail("abc@xyzc.aaa").get();
        this.userEquals(userByEmail);
    }

    @Test
    public void userServiceTest_findByUserName(){
        User userByUserName = this.userService.findByUserName("Chapu1");
        this.userEquals(userByUserName);
    }

    private User createUser() {
        User user = new User("Nano", "Chapu", "abc@xyz.com", "fdsjio");
        this.userService.createOne(user);
        userEquals(user);
        return user;
    }

    private void updateUser(User user) {
        user.setName("Nahuel");
        user.setEmail("xyz@abc.com");
        user.setUsername("Messi");
        this.userService.updateOne(user);
        userEquals(user);
    }

    private void deleteUser(User user){
        this.userService.deleteById(user.getId());
        userWasDeleted(user.getId());
    }

    private void userWasDeleted(long id){
        assertFalse(this.userService.findById(id).isPresent());
    }

    private void userEquals(User user)  {
        try {
            LOGGER.info("Se comprueba que el usuario {} sea el mismo de la base", jsonMapper.writeValueAsString(user));
        } catch (JsonProcessingException e) {
            LOGGER.info("Excepcion", e);
        }
        long id = user.getId();
        assertTrue(this.userService.findById(id).isPresent());
        assertEquals(String.format("Se espera que el nombre del usuario sea%s", user.getName()), user.getName(), this.userService.findById(id).get().getName());
        assertEquals(String.format("Se espera que el UserName sea %s", user.getUsername()), user.getUsername(), this.userService.findById(id).get().getUsername());
        assertEquals(String.format("Se espera que el email sea %s", user.getEmail()), user.getEmail(), this.userService.findById(id).get().getEmail());
    }


}
