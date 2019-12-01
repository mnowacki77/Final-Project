package com.sda.tasklist.controller.user;

import com.sda.tasklist.dto.user.PasswordChangerForm;
import com.sda.tasklist.service.user.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/change-password")
public class PasswordChangerController {

    private final UserService userService;
    private final Validator passwordChangerValidator;


    public PasswordChangerController(UserService userService, @Qualifier("passwordChangerFormValidator") Validator passwordChangerValidator) {
        this.userService = userService;
        this.passwordChangerValidator = passwordChangerValidator;
    }

    @InitBinder("passwordForm")
    public void initPasswordBinder(WebDataBinder binder) {
        binder.addValidators(passwordChangerValidator);
    }

    @GetMapping
    ModelAndView getForm() {
        ModelAndView mnv = new ModelAndView("user/passwordChanger");
        mnv.addObject("passwordForm", userService.prepareForm());
        return mnv;
    }

    @PostMapping
    String changePassword(@ModelAttribute("passwordForm") @Validated PasswordChangerForm passwordForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/passwordChanger";
        }
        if (userService.changePassword(passwordForm)) {
            return "redirect:/logout";
        } else {
            return "redirect:/change-password";
        }
    }
}
