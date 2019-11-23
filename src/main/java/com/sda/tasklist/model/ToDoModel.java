package com.sda.tasklist.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class ToDoModel {

    private long id;
    private String name;
    private String description;
    private Category category;
    private LocalDateTime creationDate;
    private LocalDateTime deadline;
    private boolean isDone;
}
