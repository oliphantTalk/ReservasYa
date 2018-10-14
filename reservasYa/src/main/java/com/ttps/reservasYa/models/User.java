package com.ttps.reservasYa.models;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="name", nullable = false, length = 45)
    private String name;

    @Column(name="userName", nullable = false, length = 100, unique = true)
    private String username;

    @Column(name="email", nullable = false, length = 50, unique = true)
    @Email
    private String email;

    @Column(name="password", nullable = false, length = 50)
    private String password;


    public User(String name, String username, String email, String password){
        this.setName(name);
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void editName(@NotNull String name) { this.name = name; } //todo que haga validaciones
    private void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void editUserName(@NotNull String username) { this.username = username; }
    private void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void editEmail(@Email String email) { this.email = email; }
    private void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void editPassword(String password) {
        this.password = password;
    }
    private void setPassword(String password) {
        this.password = password;
    }
}
