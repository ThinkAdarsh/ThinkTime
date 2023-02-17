package com.TimeSheet.SignupLogin.service.serviceImpl;

import com.TimeSheet.SignupLogin.entity.AuthRequest;
import com.TimeSheet.SignupLogin.entity.User;
import com.TimeSheet.SignupLogin.jwtUtil.JwtUtil;
import com.TimeSheet.SignupLogin.repository.UserRepository;
import com.TimeSheet.SignupLogin.service.AuthUserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthUserService  {


    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Override
    public User createNewUser(User user) {
        user.setUsertype(user.getUsertype());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        Date date=new Date();
        user.setCreated_at(new Timestamp(date.getTime()));
        user.setUpdated_at(new Timestamp(date.getTime()));
        return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user=userRepository.findByUserName(userName).get();
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
    }


}
