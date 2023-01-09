package com.casestudy.case_study.service.security.my_user_detail;

import com.casestudy.case_study.model.security.MyUserDetail;
import com.casestudy.case_study.model.security.User;
import com.casestudy.case_study.service.security.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new MyUserDetail(user);
    }


}
