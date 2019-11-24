package com.sda.tasklist.controller.todo;

import com.sda.tasklist.service.todo.ToDoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/todo")
public class ToDoController {

    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/all")
    public ModelAndView getToDos() {
        ModelAndView mnv = new ModelAndView("todo/all");
        mnv.addObject("todos", toDoService.getToDos());
        return mnv;
    }

}
