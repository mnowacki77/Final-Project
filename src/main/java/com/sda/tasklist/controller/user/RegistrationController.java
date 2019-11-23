package com.sda.tasklist.controller.user;

import com.sda.tasklist.dto.user.CreateUserForm;
import com.sda.tasklist.exception.UserExistsException;
import com.sda.tasklist.service.user.RegistrationService;
import com.sda.tasklist.validator.CreateUserFormValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final Validator userValidator;

    public RegistrationController(RegistrationService registrationService, CreateUserFormValidator userValidator) {
        this.registrationService = registrationService;
        this.userValidator = userValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @GetMapping
    ModelAndView getRegisterPage() {
        ModelAndView mv = new ModelAndView("user/register");
        mv.addObject("createUserForm", new CreateUserForm());
        return mv;
    }

    @PostMapping
    String register(@ModelAttribute @Validated CreateUserForm createUserForm, BindingResult bindingResult) throws UserExistsException {
        if (bindingResult.hasErrors()) {
            return "user/register";
        }
        registrationService.addNewUser(createUserForm);
        return "redirect:/login";
    }


}
