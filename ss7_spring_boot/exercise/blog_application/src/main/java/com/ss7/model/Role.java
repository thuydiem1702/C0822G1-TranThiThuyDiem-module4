package com.ss7.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Role {

    @Id
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private Set<UserRole> roles;

    public Role() {
    }

    public Role(Integer id, String name, Set<UserRole> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

}
