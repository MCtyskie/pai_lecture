package com.pai.application.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean status;
    private LocalDateTime registration;

    public User(int userId, String name, String lastName, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = false;
        this.registration = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "supervisor")
    private List<Task> task;
}
