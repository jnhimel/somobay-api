package com.himel.somobay.security;

import com.himel.somobay.model.User;
import com.himel.somobay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private User user;
    private UserService userService;

    @Autowired
    public ApplicationUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String number){
        user = userService.findUserByPhoneNumber(number);
        return new ApplicationUserDetails(user);
    }
}
