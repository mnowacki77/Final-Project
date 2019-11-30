package com.sda.tasklist.dto.todo;

import com.sda.tasklist.model.todo.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class CreateToDoForm {
    private long id;
    private String name;
    private String description;
    private Status status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;
}
