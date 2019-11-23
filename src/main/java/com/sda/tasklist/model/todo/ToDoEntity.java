package com.sda.tasklist.model.todo;

import com.sda.tasklist.model.Category;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ToDoEntity {

    private long id;
    private String name;
    private String description;
    private Category category;
    private LocalDateTime creationDate;
    private LocalDateTime deadline;
    private boolean isDone;
}
