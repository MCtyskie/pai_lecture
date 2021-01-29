package com.pai.application.Views;

import com.pai.application.Models.User;
import com.pai.application.Services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("adminUI/taskE")
@SpringComponent
@UIScope
public class TaskEUI extends VerticalLayout {
    private Grid<User> userGrid;
    private Button deleteUserButton;
    private Label infoLabel;

    private User selectedUser;

    @Autowired
    private UserService userService;


    @PostConstruct
    private void init(){
        userGrid=new Grid<>(User.class);
        deleteUserButton=new Button("delete user");
        infoLabel=new Label();

        userGrid.setItems(userService.returnAllUsers());
        userGrid.setColumns("userId","name","lastName","email","password","status","registration");

        userGrid.addItemClickListener(l->{
            selectedUser=l.getItem();
        });

        deleteUserButton.addClickListener(l->{
            userService.deleteUser(selectedUser.getUserId());
            userGrid.deselect(selectedUser);
            userGrid.setItems(userService.returnAllUsers());
        });
        add(userGrid,deleteUserButton,infoLabel);
    }
}
