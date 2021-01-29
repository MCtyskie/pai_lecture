package com.pai.application.Views;

import com.pai.application.Models.User;
import com.pai.application.Services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("adminUI/taskD")
@SpringComponent
@UIScope
public class TaskDUI extends VerticalLayout {
    private Grid<User> userGrid;
    private Button changeStatusButton;
    private Label infoLabel;

    private User selectedUser;

    @Autowired
    private UserService userService;

    @PostConstruct
    private void init(){
        userGrid=new Grid<>(User.class);
        changeStatusButton=new Button("change status");
        infoLabel=new Label();

        userGrid.setItems(userService.returnAllUsers());
        userGrid.setColumns("userId","name","lastName","email","password","status","registration");

        userGrid.addItemClickListener(l->{
            selectedUser=l.getItem();
        });

        changeStatusButton.addClickListener(l->{
            userService.changeUsersStatus(selectedUser.getUserId());
            userGrid.deselect(selectedUser);
            userGrid.setItems(userService.returnAllUsers());
        });
        add(userGrid,changeStatusButton,infoLabel);
    }
}
