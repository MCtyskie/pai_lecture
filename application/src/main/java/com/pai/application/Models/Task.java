package com.pai.application.Models;

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

    public Task(int taskId, String title, String description, Type type, Status status) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.dateAdded = LocalDateTime.now();
        this.type = type;
        this.status = status;
    }
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name="supervisor_id",nullable = false)
    private User supervisor;


    private enum Type{
        TASK,
        BUG,
        FEATURE
    }
    private enum Status{
        NEW,
        IN_PROGRESS,
        DONE
    }
}
