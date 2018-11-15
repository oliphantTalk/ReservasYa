package com.ttps.reservasya.models.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ttps.reservasya.models.users.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserDTO {


    private String name;
    @NotEmpty
    private String username;
    @Email
    private String email;
    @NotEmpty
    private String password;
    private String passwordConfirm;

    public UserDTO(){}

    public UserDTO(String name, String username, String email, String password){
        this.setName(name);
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
    }

    public UserDTO(User user){
        this.setName(user.getName());
        this.setUsername(user.getUsername());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    @JsonProperty(value="user_name")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
