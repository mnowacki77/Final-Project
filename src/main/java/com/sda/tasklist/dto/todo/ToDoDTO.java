package com.sda.tasklist.dto.todo;

import com.sda.tasklist.model.todo.Status;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ToDoDTO {

    private long id;
    private String name;
    private String description;
    private Status status;
    private String creationDate;
    private String deadline;
    private boolean isDone;

}
