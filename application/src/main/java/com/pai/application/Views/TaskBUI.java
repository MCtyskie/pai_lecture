package com.pai.application.Views;

import com.pai.application.Models.User;
import com.pai.application.Services.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("adminUI/taskB")
@SpringComponent
@UIScope
public class TaskBUI extends VerticalLayout {
    private Grid<User> usersGrid;

    @Autowired
    private UserService userService;


    @PostConstruct
    private void init(){
        usersGrid=new Grid<>(User.class);

        usersGrid.setItems(userService.returnAllUsers());
        usersGrid.setColumns("userId","name","lastName","email","password","status","registration");
        add(usersGrid);
    }
}
