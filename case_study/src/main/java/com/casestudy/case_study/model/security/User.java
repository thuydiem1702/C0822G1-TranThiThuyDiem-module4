package com.casestudy.case_study.model.security;

import com.casestudy.case_study.model.employee.Employee;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private Integer status = 1;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserRole> UserRoles;

    @OneToOne(mappedBy = "user")
    private Employee employee;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
