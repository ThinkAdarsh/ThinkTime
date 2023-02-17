package com.TimeSheet.SignupLogin.service;

import com.TimeSheet.SignupLogin.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.rmi.server.UID;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserService {

    public List<User> getAllUser();
    public User getUserByUserId(UUID userId);
    public User updateUser(UUID userId, User user);
    public Map<Boolean, String> deleteUser(UUID userId);


}
