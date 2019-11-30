package com.sda.tasklist.model.todo;

import com.sda.tasklist.model.user.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class ToDoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private Status status;
    private LocalDateTime creationDate;
    private LocalDate deadline;
    private boolean isDone;

    @ManyToOne
    private UserEntity user;
}
