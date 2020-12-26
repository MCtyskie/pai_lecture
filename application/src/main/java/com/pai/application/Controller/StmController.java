package com.pai.application.Controller;

import com.pai.application.Enums.Status;
import com.pai.application.Enums.Type;
import com.pai.application.Models.Task;
import com.pai.application.Models.User;
import com.pai.application.Services.TaskService;
import com.pai.application.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public boolean changeUsersStatus(
            @PathVariable("userId") int userId
    ){
        return userService.changeUsersStatus(userId);
    }

    //task e
    @DeleteMapping(value = "/users/delete/id={userId}")
    public String deleteUser(
            @RequestParam("userId") int userId
    ){
        return userService.deleteUser(userId);
    }




    //task f
    @PostMapping(value = "/tasks/create")
    public Task createTask(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("type") Type type,
            @RequestParam("status") Status status,
            @RequestParam("userId") int userId
    ){
        return taskService.createTaskByUser(title,description,type,status,userId);
    }

    //task g
    @GetMapping(value = "/tasks")
    public List<Task> getAllTasks(){
        return taskService.findAllTasksByDesc();
    }

    //task h
    @GetMapping(value = "/tasks/title={title}&status={status}&type={type}")
    public List<Task> findTaskOrTasks(
            @RequestParam("name") Optional<String> title,
            @RequestParam("status") Optional<Status> status,
            @RequestParam("type") Optional<Type> type
    ){
        return taskService.findTask(title,status,type);
    }

    //task i
    @PutMapping(value="/tasks/changeStatus/id={taskId}")
    public Task changeTasksStatus(
            @RequestParam("taskId") int taskId,
            @RequestParam("status") Status status
    ) {
            return taskService.changeTasksStatus(taskId,status);
    }

    //task j
    @DeleteMapping(value = "/tasks/delete/id={taskId}")
    public String deleteTask(
            @RequestParam("taskId") int taskId
    ){
        return taskService.deleteTask(taskId);
    }
}
