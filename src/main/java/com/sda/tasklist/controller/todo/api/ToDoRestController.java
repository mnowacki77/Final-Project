package com.sda.tasklist.controller.todo.api;

import com.sda.tasklist.dto.todo.ToDoDTO;
import com.sda.tasklist.service.todo.ToDoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/todos")
public class ToDoRestController {

    private final ToDoService toDoService;

    public ToDoRestController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getToDos() {
        List<ToDoDTO> allToDos = toDoService.getAllTodos();
        return ResponseEntity.ok().body(allToDos);
    }
}
