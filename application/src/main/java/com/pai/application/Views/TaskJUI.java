package com.pai.application.Views;

import com.pai.application.Models.Task;
import com.pai.application.Services.TaskService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("userUI/taskJ")
@SpringComponent
@UIScope
public class TaskJUI extends VerticalLayout {
    private Grid<Task> tasksGrid;
    private Label infoLabel1;
    private Button deleteTaskButton;
    private Label infoLabel2;

    private Task selectedTask;

    @Autowired
    private TaskService taskService;

    @PostConstruct
    private void init(){
        tasksGrid=new Grid<>(Task.class);
        tasksGrid.setColumns("taskId","title","description","dateAdded","type","status");
        tasksGrid.setItems(taskService.findAllTasks());
        infoLabel1=new Label();
        deleteTaskButton=new Button("DELETE TASK");
        infoLabel2=new Label();

        tasksGrid.addItemClickListener(l->{
            selectedTask=l.getItem();
            infoLabel1.setText(String.format("Full description: %s",selectedTask.getDescription()));
        });
        deleteTaskButton.addClickListener(l->{
            if(selectedTask!=null){
                int id=selectedTask.getTaskId();
                String title=selectedTask.getTitle();
                taskService.deleteTask(id);
                infoLabel2.setText(String.format("Task with id: %d and title: \"%s\" deleted successfully",id,title));
                tasksGrid.setItems(taskService.findAllTasks());
            }else{
                infoLabel2.setText("Select task");
            }
        });

        add(tasksGrid,infoLabel1,deleteTaskButton,infoLabel2);
    }
}
