package com.sda.tasklist.mapper.todo;

import com.sda.tasklist.dto.todo.ToDoDTO;
import com.sda.tasklist.model.todo.ToDoEntity;

import java.time.format.DateTimeFormatter;

public class ToDoMapper {

    private static final String datePattern = "yyyy-MM-dd HH:mm:ss";

    public static ToDoDTO map(ToDoEntity toDoEntity) {
        ToDoDTO toDoDTO = new ToDoDTO();
        toDoDTO.setId(toDoEntity.getId());
        toDoDTO.setName(toDoEntity.getName());
        toDoDTO.setDescription(toDoEntity.getDescription());
        toDoDTO.setStatus(toDoEntity.getStatus());
        toDoDTO.setCreationDate(toDoEntity.getCreationDate().format(DateTimeFormatter.ofPattern(datePattern)));
        toDoDTO.setDeadline(toDoEntity.getDeadline().format(DateTimeFormatter.ofPattern(datePattern)));
        toDoDTO.setDone(toDoEntity.isDone());
        return toDoDTO;
    }
}
