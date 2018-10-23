package com.ttps.reservasya.models;



import org.apache.catalina.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="name", nullable = false, length = 45)
    @NotEmpty
    private String name;

    @Column(name="username", nullable = false, length = 100, unique = true)
    @NotEmpty
    private String username;

    @Column(name="email", nullable = false, length = 50, unique = true)
    @Email
    private String email;

    @Column(name="password", nullable = false, length = 50)
    @NotEmpty
    private String password;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    public User(){}

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

    public void editName(@NotNull String name) { this.name = name; }

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

    public Set<Role> getRoles(){
        return roles;
    }

    public void setRoles(Set<Role> roles){
        this.roles = roles;
    }

}
