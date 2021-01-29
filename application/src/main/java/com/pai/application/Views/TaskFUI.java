package com.pai.application.Views;

import com.pai.application.Enums.Status;
import com.pai.application.Enums.Type;
import com.pai.application.Models.Task;
import com.pai.application.Models.User;
import com.pai.application.Repositories.UserRepository;
import com.pai.application.Services.TaskService;
import com.pai.application.Services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
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
import java.util.Arrays;

@Route("userUI/taskF")
@SpringComponent
@UIScope
public class TaskFUI extends VerticalLayout {
    private EmailField emailField;
    private PasswordField passwordField;
    private PasswordField passwordFieldConf;
    private Button confirmLogButton;
    private Label infoLabel1;
    //---------------------//
    private TextField titleField;
    private TextField descriptionField;
    private ComboBox<Type> typeBox;
    private ComboBox<Status> statusBox;
    private Button createTaskButton;
    private Label infoLabel2;

    private User currentUser;
    private Task createdTask;

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;


    @PostConstruct
    private void init(){
        emailField=new EmailField("email:");
        passwordField=new PasswordField("password:");
        passwordFieldConf=new PasswordField("confirm password:");
        confirmLogButton=new Button("CONFIRM");
        infoLabel1=new Label();
        //-------------------//
        titleField=new TextField("task title:");
        descriptionField=new TextField("task description:");
        typeBox=new ComboBox<>("task type");
        statusBox=new ComboBox<>("task status");
        createTaskButton=new Button("CREATE");
        infoLabel2=new Label();

        titleField.setEnabled(false);
        descriptionField.setEnabled(false);
        typeBox.setEnabled(false);
        typeBox.setAllowCustomValue(false);
        typeBox.setItems(Arrays.asList(Type.values()));
        statusBox.setEnabled(false);
        statusBox.setAllowCustomValue(false);
        statusBox.setItems(Arrays.asList(Status.values()));
        createTaskButton.setEnabled(false);



        confirmLogButton.addClickListener(l->{
            if(passwordField.getValue().equals(passwordFieldConf.getValue())){
                String email=emailField.getValue();
                String password=passwordField.getValue();
                currentUser=userService.findUserByEmailAndPassword(email,password);
                if(currentUser!=null){
                    if(currentUser.isStatus()){
                        titleField.setEnabled(true);
                        descriptionField.setEnabled(true);
                        typeBox.setEnabled(true);
                        statusBox.setEnabled(true);
                        createTaskButton.setEnabled(true);
                        infoLabel1.setText(String.format("Logged as %s %s",currentUser.getName(),currentUser.getLastName()));
                    }else {
                        infoLabel1.setText("User is inactive. Contact admin");
                    }
                }else{
                    infoLabel1.setText("No such user");
                }
            }else{
                infoLabel1.setText("Passwords arent equal!");
            }

        });

        createTaskButton.addClickListener(l->{
            if(!titleField.isEmpty() && !descriptionField.isEmpty() && !typeBox.isEmpty() && !statusBox.isEmpty()){
                String title=titleField.getValue();
                String description=descriptionField.getValue();
                Type type=typeBox.getValue();
                Status status=statusBox.getValue();
                createdTask=taskService.createTaskByUser(title,description,type,status,currentUser.getUserId());
                infoLabel2.setText(String.format("Task with id: %d and title: \"%s\" added successfully.",
                        createdTask.getTaskId(),createdTask.getTitle()));
            }else{
                infoLabel2.setText("Provide all data");
            }
        });
        add(emailField,passwordField,passwordFieldConf,confirmLogButton,infoLabel1,
                titleField,descriptionField,typeBox,statusBox,createTaskButton,infoLabel2);
    }
}
