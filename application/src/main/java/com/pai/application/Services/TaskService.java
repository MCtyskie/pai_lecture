package com.pai.application.Services;

import com.pai.application.Enums.Status;
import com.pai.application.Enums.Type;
import com.pai.application.Models.Task;
import com.pai.application.Models.User;
import com.pai.application.Repositories.TaskRepository;
import com.pai.application.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    //task f
    public Task createTaskByUser(String title, String description, Type type, Status status, Integer userId){
        Optional<User> supervisor=userRepository.findUserByUserId(userId);
        if(supervisor.isPresent()){
            User user=supervisor.get();
            Task newTask=new Task(title,description,type,status,user);
            return taskRepository.save(newTask);
        }
        return null;
    }

    //task g
    public List<Task> findAllTasksByDesc(){
        return taskRepository.findAllOrdersByDateDesc();
    }

    //task h
    public List<Task> findTask(Optional<String> title,Optional<Status>status,Optional<Type>type){
        if(title.isPresent()){
            return taskRepository.findByTitle(title.get());
        }else if(status.isPresent()){
            return taskRepository.findByStatus(status.get());
        }else if(type.isPresent()){
            return taskRepository.findByType(type.get());
        }
        return null;
    }

    //task i
    public Task changeTasksStatus(Integer taskId, Status newStatus){
        Optional<Task> currentTask=taskRepository.findById(taskId);
        if(currentTask.isPresent()){
            Task task=currentTask.get();
            if(newStatus != task.getStatus()){
                task.setStatus(newStatus);
                taskRepository.save(task);
                return task;
            }
        }
        return null;
    }

    //task j
    public String deleteTask(Integer taskId){
        Optional<Task> currentTask=taskRepository.findById(taskId);
        if(currentTask.isPresent()){
            Task task=currentTask.get();
            String message=String.format("Task no %d deleted",task.getTaskId());
            taskRepository.delete(task);
            return message;
        }
        return "";
    }

    //helpers:
    public List<Task> findAllTasks(){
        return taskRepository.findAll();
    }
    public Task findTaskById(Integer  taskId){
        return taskRepository.findById(taskId).orElse(null);
    }
}
