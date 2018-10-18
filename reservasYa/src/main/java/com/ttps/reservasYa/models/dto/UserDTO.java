package com.ttps.reservasYa.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ttps.reservasYa.models.User;

public class UserDTO {

    private long id;
    private String name;
    private String username;
    private String email;
    private String password;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
