package com.ttps.reservasya.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.ttps.reservasya.models.User;
import com.ttps.reservasya.AbstractConfigurationTest;
import com.ttps.reservasya.utils.CustomObjectMapper;
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
public class UserServiceTest extends AbstractConfigurationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);
    @Autowired
    public UserService userService;
    private static Boolean setUpIsDone = false;

    @Before
    public void populateH2() throws IOException {
        if(!setUpIsDone) {
            URL url = new URL("file:src/test/resources/users_h2.json");
            List<User> users = CustomObjectMapper.getMapper().readValue(url, new TypeReference<List<User>>() {
            });
            users.forEach(val -> {if(!this.userService.findByEmail(val.getEmail()).isPresent()) this.userService.createUser(val);});
            setUpIsDone = true;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void userServiceTest_ValidationOnCreateFails(){
        User userBadEmail = new User("nano", "asdf", "asdfoj", "fdsjio");
        this.userService.createUser(userBadEmail);
    }

    @Test(expected = TransactionSystemException.class)
    public void userServiceTest_ValidationOnUpdateFails(){
        User userWillFail = this.userService.findOne(5L).get();
        userWillFail.editEmail("bad_email");
        this.userService.updateUser(userWillFail);
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
        this.userService.createUser(user);
        userEquals(user);
        return user;
    }

    private void updateUser(User user) {
        user.editName("Nahuel");
        user.editEmail("xyz@abc.com");
        user.editUserName("Messi");
        this.userService.updateUser(user);
        userEquals(user);
    }

    private void deleteUser(User user){
        this.userService.deleteUser(user);
        userWasDeleted(user.getId());
    }

    private void userWasDeleted(long id){
        assertFalse(this.userService.findOne(id).isPresent());
    }

    private void userEquals(User user)  {
        try {
            LOGGER.info(String.format("Se comprueba que el usuario %s sea el mismo de la base", CustomObjectMapper.getMapper().writeValueAsString(user)));
        } catch (JsonProcessingException e) {
            LOGGER.info("Excepcion", e);
        }
        long id = user.getId();
        assertTrue(this.userService.findOne(id).isPresent());
        assertEquals(String.format("Se espera que el nombre del usuario sea%s", user.getName()), user.getName(), this.userService.findOne(id).get().getName());
        assertEquals(String.format("Se espera que el UserName sea %s", user.getUsername()), user.getUsername(), this.userService.findOne(id).get().getUsername());
        assertEquals(String.format("Se espera que el email sea %s", user.getEmail()), user.getEmail(), this.userService.findOne(id).get().getEmail());
    }


}
