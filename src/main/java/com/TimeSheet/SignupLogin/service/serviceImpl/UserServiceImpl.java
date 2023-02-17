package com.TimeSheet.SignupLogin.service.serviceImpl;

import com.TimeSheet.SignupLogin.customException.UserNotFoundException;
import com.TimeSheet.SignupLogin.entity.User;
import com.TimeSheet.SignupLogin.enums.UserType;
import com.TimeSheet.SignupLogin.repository.UserRepository;
import com.TimeSheet.SignupLogin.service.UserDetailsGroup;
import com.TimeSheet.SignupLogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;


@Service
public class UserServiceImpl implements UserService  {

    @Autowired
    private UserRepository userRepository;

    public User giveAccessToUser(UUID userId, UserType userType, Principal principal) {
        User user=userRepository.findById(userId).get();
        List<String> activeRoles= getRolesByUser(principal);
        if(activeRoles.contains(userType)){

        }
        return userRepository.save(user);

    }

    private List<String> getRolesByUser(Principal principal){
        UserType usertype= getLoggedInUser(principal).getUsertype();
        if(usertype.equals(UserType.SALES)) {

        }
        if (usertype.equals(UserType.MANAGER)){

        }
        if(usertype.equals(UserType.TEAMLEADER)){

        }
        if(usertype.equals(UserType.ENGINEER)){

        }
        return Collections.emptyList();
    }

    private User getLoggedInUser(Principal principal){
        return userRepository.findByUserName(principal.getName()).get();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUserId(UUID userId) {
        return userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("Given user Id is not exist.Please Enter valid user Id..."));
    }

    @Override
    public User updateUser(UUID userId, User user) {
        System.out.println("userId = " + userId);
        User newUser= userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("Given user Id is not exist.Please Enter valid user Id..."));
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setEmail(user.getEmail());
        newUser.setNumber(user.getNumber());
        newUser.setUsertype(user.getUsertype());
        newUser.setRole(user.getRole());
        newUser.setBirthday(user.getBirthday());
        newUser.setCreated_at(user.getCreated_at());
        newUser.setUpdated_at(user.getUpdated_at());

        return userRepository.save(newUser);
    }

    @Override
    public Map<Boolean, String> deleteUser(UUID userId) {

        User deleteUser= userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("Given user Id is not exist.Please Enter valid user Id..."));
        userRepository.delete(deleteUser);
        Map<Boolean, String> response=new HashMap<>();
        response.put(true,"deleted");

        return response;
    }



}
