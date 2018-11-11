package com.ttps.reservasya.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name ="Role")
public class Role implements Serializable {

    private Long id;
    private String name;
    private Set<Permission> permissions;

    public Role() {
    }

    ;

    public Role(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ROLE_PERMISSION", joinColumns = {@JoinColumn(name = "ROLE_ID")}, inverseJoinColumns = {@JoinColumn(name = "PERMISSION_ID")})
    public Set<Permission> getPermissions() {
        return this.permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
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
        Role role = (Role) o;
        // field comparison
        return Objects.equals(name, role.name);
    }

}
