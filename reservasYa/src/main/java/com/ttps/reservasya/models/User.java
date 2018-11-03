package com.ttps.reservasya.models;





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

    @Column(name="name", length = 20)
    private String name;

    @Column(name="username", nullable = false, length = 20, unique = true)
    @NotEmpty
    private String username;

    @Column(name="email", nullable = false, length = 20, unique = true)
    @Email
    private String email;

    @Column(name="password", nullable = false)
    @NotEmpty
    private String password;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Transient
    private String passwordConfirm;

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

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

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void editName(@NotNull String name) { this.name = name; }

    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }

    public void editUserName(@NotNull String username) { this.username = username; }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }

    public void editEmail(@Email String email) { this.email = email; }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void editPassword(String password) {
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles(){
        return roles;
    }

    public void setRoles(Set<Role> roles){
        this.roles = roles;
    }

}
