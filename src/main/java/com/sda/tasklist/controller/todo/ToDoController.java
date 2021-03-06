package com.sda.tasklist.controller.todo;

import com.sda.tasklist.dto.todo.CreateToDoForm;
import com.sda.tasklist.dto.todo.ToDoDTO;
import com.sda.tasklist.exception.ToDoNotExistsException;
import com.sda.tasklist.model.todo.Sorting;
import com.sda.tasklist.model.todo.Status;
import com.sda.tasklist.service.todo.ToDoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/todo")
public class ToDoController {

    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/all")
    public ModelAndView getToDos(@RequestParam(defaultValue = "DEFAULT", required = false) String sort, HttpSession session) {
        ModelAndView mnv = new ModelAndView("todo/all");
        Sorting sorting = Sorting.DEFAULT;
        try {
            sorting = Sorting.valueOf(sort.toUpperCase());
        } catch (Exception e) {
            sorting = Sorting.DEFAULT;
        }
        mnv.addObject("todos", toDoService.getToDos(sorting));
        mnv.addObject("status", Status.values());
        session.setAttribute("quantity", toDoService.getQuantity());
        return mnv;
    }

    @GetMapping("/add")
    public ModelAndView addToDos() {
        ModelAndView mnv = new ModelAndView("todo/add");
        mnv.addObject("createForm", new CreateToDoForm());
        return mnv;
    }

    @PostMapping("/add")
    public String add(@ModelAttribute CreateToDoForm createToDoForm) {
        toDoService.addToDo(createToDoForm);
        return "redirect:/todo/all";
    }

    @PostMapping("/delete")
    public String add(@RequestParam String todoId) {
        toDoService.deleteById(Long.valueOf(todoId));
        return "redirect:/todo/all";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable String id) throws ToDoNotExistsException {
        ModelAndView mnv = new ModelAndView("todo/edit");
        mnv.addObject("editForm", toDoService.findById(Long.valueOf(id)));
        mnv.addObject("statusOptions", Status.values());
        return mnv;
    }

    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute ToDoDTO toDoDTO, @PathVariable String id) throws ToDoNotExistsException {
        toDoDTO.setId(Long.valueOf(id));
        toDoService.editToDo(toDoDTO);
        return "redirect:/todo/all";
    }

    @PostMapping("/done/{id}")
    public String done(@PathVariable String id) throws ToDoNotExistsException {
        toDoService.markDone(Long.valueOf(id));
        return "redirect:/todo/all";
    }

    @PostMapping("/undone/{id}")
    public String undone(@PathVariable String id) throws ToDoNotExistsException {
        toDoService.markUnDone(Long.valueOf(id));
        return "redirect:/todo/all";
    }

    @GetMapping("/info/{id}")
    public ModelAndView info(@PathVariable String id) throws ToDoNotExistsException {
        ModelAndView mnv = new ModelAndView("todo/info");
        mnv.addObject("todo", toDoService.findById(Long.valueOf(id)));
        return mnv;
    }


}
