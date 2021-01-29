package com.pai.application.Views;

import com.pai.application.Models.Task;
import com.pai.application.Models.User;
import com.pai.application.Services.TaskService;
import com.pai.application.Services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("userUI/taskG")
@SpringComponent
@UIScope
public class TaskGUI extends VerticalLayout {
    private Grid<Task> tasksGrid;
    private Label infoLabel;

    private Task selectedTask;

    @Autowired
    private TaskService taskService;


    @PostConstruct
    private void init(){
        tasksGrid=new Grid<>(Task.class);
        tasksGrid.setItems(taskService.findAllTasksByDesc());
        tasksGrid.setColumns("taskId","title","description","dateAdded","type","status");
        infoLabel=new Label();

        tasksGrid.addItemClickListener(l->{
            selectedTask=l.getItem();
            infoLabel.setText(String.format("Full description: %s",selectedTask.getDescription()));
        });
        add(tasksGrid,infoLabel);
    }
}
