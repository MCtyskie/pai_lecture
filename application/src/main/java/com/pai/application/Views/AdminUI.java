package com.pai.application.Views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import javax.annotation.PostConstruct;

@Route("adminUI")
@SpringComponent
@UIScope
public class AdminUI extends VerticalLayout {
    private Button taskAButton;
    private Button taskBButton;
    private Button taskCButton;
    private Button taskDButton;
    private Button taskEButton;


    @PostConstruct
    private void init(){
        taskAButton=new Button("CREATE NEW USER");
        taskBButton=new Button("GET ALL USERS");
        taskCButton=new Button("GET USER BY ID OR EMAIL");
        taskDButton=new Button("CHANGE STATUS");
        taskEButton=new Button("DELETE USER");

        taskAButton.addClickListener(l->{
            UI.getCurrent().getPage().setLocation("/adminUI/taskA");
        });
        taskBButton.addClickListener(l->{
            UI.getCurrent().getPage().setLocation("/adminUI/taskB");
        });
        taskCButton.addClickListener(l->{
            UI.getCurrent().getPage().setLocation("/adminUI/taskC");
        });
        taskDButton.addClickListener(l->{
            UI.getCurrent().getPage().setLocation("/adminUI/taskD");
        });
        taskEButton.addClickListener(l->{
            UI.getCurrent().getPage().setLocation("/adminUI/taskE");
        });

        add(taskAButton,taskBButton,taskCButton,taskDButton,taskEButton);
    }
}
