package com.sda.tasklist.service.todo;

import com.sda.tasklist.dto.todo.CreateToDoForm;
import com.sda.tasklist.dto.todo.ToDoDTO;
import com.sda.tasklist.exception.ToDoNotExistsException;

import java.util.List;

public interface ToDoService {

    List<ToDoDTO> getToDos(String sort);

    void addToDo(CreateToDoForm createToDoForm);

    void deleteById(Long id);

    String getUserLogin();

    ToDoDTO findById(Long id) throws ToDoNotExistsException;

    void editToDo(ToDoDTO toDoDTO) throws ToDoNotExistsException;

    List<ToDoDTO> getAllToDos();

    void markDone(Long id) throws ToDoNotExistsException;

    Long getQuantity();

}
