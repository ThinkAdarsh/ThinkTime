package com.TimeSheet.SignupLogin.service;

import com.TimeSheet.SignupLogin.entity.AuthRequest;
import com.TimeSheet.SignupLogin.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthUserService extends UserDetailsService  {

    public User createNewUser(User user);

}
