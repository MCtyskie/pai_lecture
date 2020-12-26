package com.pai.application.Models;

import com.pai.application.Enums.Status;
import com.pai.application.Enums.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;
    private String title;
    private String description;
    @Column(name = "date_added")
    private LocalDateTime dateAdded;
    private Type type;
    private Status status;

    public Task(String title, String description, Type type, Status status) {
        this.title = title;
        this.description = description;
        this.dateAdded = LocalDateTime.now();
        this.type = type;
        this.status = status;
    }
    public Task(String title, String description, Type type, Status status, User supervisor) {
        this.title = title;
        this.description = description;
        this.dateAdded = LocalDateTime.now();
        this.type = type;
        this.status = status;
        this.supervisor=supervisor;
    }
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name="supervisor_id",nullable = false)
    private User supervisor;


}
