package com.sda.tasklist.controller.todo;

import com.sda.tasklist.dto.todo.CreateToDoForm;
import com.sda.tasklist.service.todo.ToDoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/add")
    public ModelAndView addToDos(){
        ModelAndView mnv = new ModelAndView("todo/add");
        mnv.addObject("createForm", new CreateToDoForm());
        return mnv;
    }

    @PostMapping("/add")
    String add (@ModelAttribute CreateToDoForm createToDoForm){
        toDoService.addToDo(createToDoForm);
        return "redirect:/todo/all";
    }

    @GetMapping("/{id}/delete")
    String add (@PathVariable Long id){
        toDoService.deleteById(id);
        return "redirect:/todo/all";
    }



}
