package com.sda.tasklist.service.todo;

import com.sda.tasklist.dto.todo.CreateToDoForm;
import com.sda.tasklist.dto.todo.ToDoDTO;

import java.util.List;

public interface ToDoService {

    List<ToDoDTO> getToDos();
    void addToDo(CreateToDoForm createToDoForm);
    String getUserLogin();

}
