package com.sda.tasklist.dto.todo;

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
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;
}
