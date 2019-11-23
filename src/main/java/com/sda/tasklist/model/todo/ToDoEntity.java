package com.sda.tasklist.model.todo;

import com.sda.tasklist.model.Category;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Builder
@Entity
public class ToDoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private Category category;
    private LocalDateTime creationDate;
    private LocalDateTime deadline;
    private boolean isDone;
}
