package com.TimeSheet.SignupLogin.controller;

import com.TimeSheet.SignupLogin.entity.AuthRequest;
import com.TimeSheet.SignupLogin.entity.User;
import com.TimeSheet.SignupLogin.jwtUtil.JwtUtil;
import com.TimeSheet.SignupLogin.service.serviceImpl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/signup")
    public ResponseEntity<User> createNewUser(@RequestBody User user){
        return ResponseEntity.ok().body(authService.createNewUser(user));
    }


    @PostMapping("/login")
    public String generateToken(@RequestBody User user) throws Exception {
        try {
            authService.loadUserByUsername(user.getUserName());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(user.getUserName());


    }


}
