package com.sda.tasklist.dto.todo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ToDoDTO {

    private long id;
    private String name;
    private String description;
    private String status;
    private String creationDate;
    private String deadline;
    private boolean isDone;

}
