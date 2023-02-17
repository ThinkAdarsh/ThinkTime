package com.TimeSheet.SignupLogin.controller;

import com.TimeSheet.SignupLogin.entity.User;
import com.TimeSheet.SignupLogin.enums.UserType;
import com.TimeSheet.SignupLogin.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('SALES') or hasAuthority('MANAGER')")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUser());
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('SALES') or hasAuthority('MANAGER')")
    public ResponseEntity<User> getUserById(@PathVariable UUID userId){
        return ResponseEntity.ok().body(userService.getUserByUserId(userId));
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('SALES')")
    public ResponseEntity<User> updateUser(@PathVariable UUID userId,@RequestBody User user){
        return ResponseEntity.ok().body(userService.updateUser(userId, user));
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('SALES')")
    public ResponseEntity<Map<Boolean, String>> deleteUser(@PathVariable UUID userId){
        return ResponseEntity.ok().body(userService.deleteUser(userId));
    }

    @GetMapping("/access/{userId}/{userType}")
    @PreAuthorize("hasAuthority('SALES') or hasAuthority('MANAGER')")
    public ResponseEntity<User> giveAccessToUser(@PathVariable UUID userId,@RequestBody UserType userType, Principal principal){
        return ResponseEntity.ok().body(userService.giveAccessToUser(userId, userType, principal));
    }






}
