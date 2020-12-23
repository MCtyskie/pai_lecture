package com.pai.application.Controller;

import com.pai.application.Models.User;
import com.pai.application.Services.TaskService;
import com.pai.application.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StmController {
    private UserService userService;
    private TaskService taskService;
    @Autowired
    public StmController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    //task a
    @PostMapping("/users/registration")
    public User registerUser(
            @RequestParam("name") String name,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ){
        //return userService.insertUser(new User(name,lastName,email,password, LocalDateTime.now(), "",false));
        return userService.addNewUser(new User(name,lastName,email,password));
    }

    //task b
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.returnAllUsers();
    }

    //task c
    @GetMapping("/users/findUser={email}")
    public User getUserByIdOrEmail(
            @RequestParam("email") String email
    ){
        return userService.findUserByIdOrEmail(email);
    }

    @GetMapping("/users/findUser={userId}")
    public User getUserByIdOrEmail(
            @RequestParam("userId") int userId
    ){
        return userService.findUserByIdOrEmail(userId);
    }

    //task d
    @PutMapping("/users/changeStatus/id={userId}")
    public boolean activateUser(
            @PathVariable("userId") int userId
    ){
        return userService.changeUsersStatus(userId);
    }
}
