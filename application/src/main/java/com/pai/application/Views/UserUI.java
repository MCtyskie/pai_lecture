package com.pai.application.Views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import javax.annotation.PostConstruct;

@Route("userUI")
@SpringComponent
@UIScope
public class UserUI extends VerticalLayout {
    private Button taskFButton;
    private Button taskGButton;
    private Button taskHButton;
    private Button taskIButton;
    private Button taskJButton;


    @PostConstruct
    private void init() {
        taskFButton = new Button("CREATE NEW TASK");
        taskGButton = new Button("GET ALL TASKS ORDERED BY DESC");
        taskHButton = new Button("FIND TASK BY NAME/STATUS/TYPE");
        taskIButton = new Button("CHANGE TASK'S STATUS");
        taskJButton = new Button("DELETE TASK");

        taskFButton.addClickListener(l -> {
            UI.getCurrent().getPage().setLocation("/userUI/taskF");
            //UI.getCurrent().getSession().setAttribute("xd",);
        });
        taskGButton.addClickListener(l -> {
            UI.getCurrent().getPage().setLocation("/userUI/taskG");
        });
        taskHButton.addClickListener(l -> {
            UI.getCurrent().getPage().setLocation("/userUI/taskH");
        });
        taskIButton.addClickListener(l -> {
            UI.getCurrent().getPage().setLocation("/userUI/taskI");
        });
        taskJButton.addClickListener(l -> {
            UI.getCurrent().getPage().setLocation("/userUI/taskJ");
        });

        add(taskFButton, taskGButton, taskHButton, taskIButton, taskJButton);
    }
}
