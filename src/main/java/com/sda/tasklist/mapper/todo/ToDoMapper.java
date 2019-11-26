package com.sda.tasklist.mapper.todo;

import com.sda.tasklist.dto.todo.CreateToDoForm;
import com.sda.tasklist.dto.todo.ToDoDTO;
import com.sda.tasklist.model.todo.ToDoEntity;

import java.time.format.DateTimeFormatter;

public class ToDoMapper {

    private static final String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
    private static final String datePattern = "yyyy-MM-dd";

    public static ToDoDTO map(ToDoEntity toDoEntity) {
        ToDoDTO toDoDTO = new ToDoDTO();
        toDoDTO.setId(toDoEntity.getId());
        toDoDTO.setName(toDoEntity.getName());
        toDoDTO.setDescription(toDoEntity.getDescription());
        toDoDTO.setStatus(toDoEntity.getStatus());
        toDoDTO.setCreationDate(toDoEntity.getCreationDate().format(DateTimeFormatter.ofPattern(dateTimePattern)));
        toDoDTO.setDeadline(toDoEntity.getDeadline().format(DateTimeFormatter.ofPattern(datePattern)));
        toDoDTO.setDone(toDoEntity.isDone());
        return toDoDTO;
    }

    public static ToDoEntity map(CreateToDoForm createToDoForm){
        ToDoEntity toDoEntity = new ToDoEntity();
        toDoEntity.setName(createToDoForm.getName());
        toDoEntity.setDescription(createToDoForm.getDescription());
        toDoEntity.setStatus(createToDoForm.getStatus());
        toDoEntity.setDeadline(createToDoForm.getDeadline());
        return toDoEntity;


    }
}
