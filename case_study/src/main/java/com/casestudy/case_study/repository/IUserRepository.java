package com.casestudy.case_study.repository;

import com.casestudy.case_study.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String userName);

}
