package com.pai.application.Views;

import com.pai.application.Models.User;
import com.pai.application.Services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("adminUI/taskC")
@SpringComponent
@UIScope
public class TaskCUI extends VerticalLayout {
    private TextField idEmailField;
    private Button confirmButton;
    private Grid<User> userGrid;
    private Label infoLabel;

    @Autowired
    private UserService userService;


    @PostConstruct
    private void init(){
        idEmailField=new TextField("id or email:");
        confirmButton=new Button("Search");
        userGrid=new Grid<>(User.class);
        userGrid.setColumns("userId","name","lastName","email","password","status","registration");
        infoLabel=new Label();

        confirmButton.addClickListener(l->{
            if(idEmailField.getValue().matches("\\d+")){
                if(userService.findUserByIdOrEmail(Integer.parseInt(idEmailField.getValue()))!=null){
                    userGrid.setItems(userService.findUserByIdOrEmail(Integer.parseInt(idEmailField.getValue())));
                    infoLabel.setText("Found by id");
                }else{
                    userGrid.setItems();
                    infoLabel.setText("No user with this id or email");
                }
            }else {
                if (userService.findUserByIdOrEmail(idEmailField.getValue()) != null) {

                    userGrid.setItems(userService.findUserByIdOrEmail(idEmailField.getValue()));
                    infoLabel.setText("Found by email");
                }else{
                    infoLabel.setText("No user with this id or email");
                }
            }
        });
        add(idEmailField,confirmButton,userGrid,infoLabel);
    }
}
