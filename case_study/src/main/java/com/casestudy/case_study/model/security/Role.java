package com.casestudy.case_study.model.security;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer status = 1;

    @OneToMany(mappedBy = "role")
    private Set<UserRole> UserRoles;

    public Role() {
    }

    public Role(Integer id, String name, Integer status, Set<UserRole> userRoles) {
        this.id = id;
        this.name = name;
        this.status = status;
        UserRoles = userRoles;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<UserRole> getUserRoles() {
        return UserRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        UserRoles = userRoles;
    }
}
