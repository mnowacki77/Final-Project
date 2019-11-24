package com.sda.tasklist.controller.todo;

import com.sda.tasklist.dto.todo.ToDoDTO;
import com.sda.tasklist.service.todo.ToDoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/todo")
public class ToDoRestController {

    private final ToDoService toDoService;

    public ToDoRestController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/all")
    public List<ToDoDTO> getToDos() {
        return toDoService.getToDos();
    }
}
