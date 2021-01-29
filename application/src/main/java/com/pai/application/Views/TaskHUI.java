package com.pai.application.Views;

import com.pai.application.Enums.Status;
import com.pai.application.Enums.Type;
import com.pai.application.Models.Task;
import com.pai.application.Services.TaskService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

@Route("userUI/taskH")
@SpringComponent
@UIScope
public class TaskHUI extends VerticalLayout {
    private TextField titleField;
    private ComboBox<Status> statusBox;
    private ComboBox<Type> typeBox;
    private Button searchButton;
    private Grid<Task> taskGrid;
    private Label infoLabel;

    @Autowired
    private TaskService taskService;


    @PostConstruct
    private void init(){
        titleField=new TextField("task title:");
        statusBox=new ComboBox<>("task status");
        statusBox.setAllowCustomValue(false);
        statusBox.setItems(Arrays.asList(Status.values()));
        typeBox=new ComboBox<>("task type:");
        typeBox.setAllowCustomValue(false);
        typeBox.setItems(Arrays.asList(Type.values()));
        searchButton=new Button("SEARCH");
        taskGrid=new Grid<>(Task.class);
        taskGrid.setColumns("taskId","title","description","dateAdded","type","status");
        infoLabel=new Label();

        titleField.addInputListener(l->{
            statusBox.setValue(null);
            typeBox.setValue(null);
        });
        statusBox.addValueChangeListener(l->{
            titleField.setValue("");
            typeBox.setValue(null);
        });
        typeBox.addValueChangeListener(l->{
            titleField.setValue("");
            statusBox.setValue(null);
        });
        searchButton.addClickListener(l->{
            if(!titleField.isEmpty() || !statusBox.isEmpty() || !typeBox.isEmpty()){
                taskGrid.setItems(taskService.findTask(titleField.getOptionalValue(),statusBox.getOptionalValue(),typeBox.getOptionalValue()));
            }else{
                infoLabel.setText("provide some data!");
            }

        });
        taskGrid.addItemClickListener(l->{
            infoLabel.setText(String.format("Full description: %s",l.getItem().getDescription()));
        });
        add(titleField,statusBox,typeBox,searchButton,taskGrid,infoLabel);
    }
}
