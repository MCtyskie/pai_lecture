package com.pai.application.Views;

import com.pai.application.Enums.Status;
import com.pai.application.Models.Task;
import com.pai.application.Services.TaskService;
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
import java.util.Arrays;

@Route("userUI/taskI")
@SpringComponent
@UIScope
public class TaskIUI extends VerticalLayout {
    private Grid<Task> tasksGrid;
    private ComboBox<Status> statusBox;
    private Button confirmButton;
    private Label infoLabel1;
    private Label infoLabel2;

    private Task selectedTask;

    @Autowired
    private TaskService taskService;


    @PostConstruct
    private void init(){
        tasksGrid=new Grid<>(Task.class);
        tasksGrid.setColumns("taskId","title","description","dateAdded","type","status");
        tasksGrid.setItems(taskService.findAllTasks());
        statusBox=new ComboBox<>("update status:");
        statusBox.setAllowCustomValue(false);
        statusBox.setItems(Arrays.asList(Status.values()));
        confirmButton=new Button("UPDATE");
        infoLabel1=new Label();
        infoLabel2=new Label();

        tasksGrid.addItemClickListener(l->{
            selectedTask=l.getItem();
            infoLabel1.setText(String.format("Full description: %s",selectedTask.getDescription()));
        });
        confirmButton.addClickListener(l->{
            if(selectedTask!=null){
                Status prevStatus=taskService.findTaskById(selectedTask.getTaskId()).getStatus();
                taskService.changeTasksStatus(selectedTask.getTaskId(),statusBox.getValue());
                Status newStatus=taskService.findTaskById(selectedTask.getTaskId()).getStatus();
                infoLabel2.setText(String.format("Task with id %d changed status from %s to %s.",
                        selectedTask.getTaskId(),prevStatus,newStatus));
                tasksGrid.setItems(taskService.findAllTasks());
            }else{
                infoLabel2.setText("Select task");
            }
        });
        add(tasksGrid,infoLabel1,statusBox,confirmButton,infoLabel2);
    }
}
