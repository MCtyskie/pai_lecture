package com.pai.application.Services;

import com.pai.application.Models.User;
import com.pai.application.Repositories.TaskRepository;
import com.pai.application.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    //task a
    public User addNewUser(User user){
        User u=userRepository.findUserByEmail(user.getEmail()).orElse(null);
        if(u==null){
            return userRepository.save(user);
        }
        return null;
    }
    //task b
    public List<User> returnAllUsers(){
        return userRepository.findAll();
    }

    //task c
    public User findUserByIdOrEmail(int id){
        User u=userRepository.findUserByUserId(id).orElse(null);
        return u;
    }
    public User findUserByIdOrEmail(String email){
        User u=userRepository.findUserByEmail(email).orElse(null);
        return u;
    }

    //task d
    public boolean changeUsersStatus(int userId){
        User user=userRepository.findUserByUserId(userId).orElse(null);
        boolean currentStatus=user.isStatus();
        user.setStatus(!currentStatus);
        userRepository.save(user);
        return !currentStatus;
    }

    //task e
}
