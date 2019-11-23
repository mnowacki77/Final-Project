package com.sda.tasklist.controller;

import com.sda.tasklist.exception.UserExistsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserExistsException.class)
    ModelAndView handleUserExistsException(UserExistsException e) {
        ModelAndView mv = new ModelAndView("errorPage");
        mv.addObject("message", e.getMessage());
        return mv;
    }
}