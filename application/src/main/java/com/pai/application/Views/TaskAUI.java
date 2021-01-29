package com.pai.application.Views;

import com.pai.application.Models.User;
import com.pai.application.Repositories.UserRepository;
import com.pai.application.Services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("adminUI/taskA")
@SpringComponent
@UIScope
public class TaskAUI extends VerticalLayout {
    private TextField nameField;
    private TextField lastNameField;
    private EmailField emailField;
    private PasswordField passwordField;
    private PasswordField passwordFieldConf;
    private Button confirmButton;
    private Label infoLabel;

    private User user;

    @Autowired
    private UserService userService;


    @PostConstruct
    private void init(){
        nameField=new TextField("name:");
        lastNameField=new TextField("last name:");
        emailField=new EmailField("email:");
        passwordField=new PasswordField("password:");
        passwordFieldConf=new PasswordField("confirm password");
        confirmButton=new Button("CREATE");
        infoLabel=new Label();

        confirmButton.addClickListener(l->{
            if(!nameField.isEmpty() && !lastNameField.isEmpty() && !emailField.isEmpty() &&
            !passwordField.isEmpty() && !passwordFieldConf.isEmpty() &&
            (passwordField.getValue().equals(passwordFieldConf.getValue()))){
                String name=nameField.getValue();
                String lastName=lastNameField.getValue();
                String email=emailField.getValue();
                String password=passwordField.getValue();
                User u=new User(name,lastName,email,password);
                user=userService.addNewUser(u);
                if(user!=null){
                    infoLabel.setText(String.format("User %s %s added correctly!",name,lastName));
                }else{
                    infoLabel.setText(String.format("Error occured. Maybe user already exists?"));
                }
            }else{
                infoLabel.setText("Provide all data and make sure that passwords are equal");
            }

        });
        add(nameField,lastNameField,emailField,passwordField,passwordFieldConf,confirmButton,infoLabel);
    }
}
