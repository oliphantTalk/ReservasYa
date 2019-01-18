package com.ttps.reservasya.models.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ttps.reservasya.models.user.role.Role;
import com.ttps.reservasya.models.user.settings.UserSettings;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="User")
public class User implements Serializable {

    private long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Role role;
    private String passwordConfirm;

    public User(){}

    public User(String email, String username, String password){
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }


    public User(String name, String username, String email, String password){
        this.setName(name);
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", length = 20)
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @Column(name = "user_name", nullable = false, length = 20, unique = true)
    @JsonProperty(value = "user_name")
    public String getUsername() {
        return username;
    }

    public void setUsername(@NotNull String username) {
        this.username = username;
    }

    @Column(name = "email", length = 30, unique = true)
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false)
    @NotEmpty
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JoinColumn(name = "ROLE_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        User user = (User) o;
        // field comparison
        return Objects.equals(id, user.id)
                && Objects.equals(email, user.email)
                && Objects.equals(username, user.username);
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
