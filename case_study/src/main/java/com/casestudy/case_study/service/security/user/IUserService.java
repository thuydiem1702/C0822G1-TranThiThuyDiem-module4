package com.casestudy.case_study.service.security.user;

import com.casestudy.case_study.model.security.User;
import com.casestudy.case_study.service.IGeneralService;

public interface IUserService extends IGeneralService<User> {

    User findUserByUsername(String username);

}
