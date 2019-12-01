package com.sda.tasklist.controller.user;

import com.sda.tasklist.dto.user.PasswordChangerForm;
import com.sda.tasklist.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/password")
public class PasswordChangerController {

    private final UserService userService;

    public PasswordChangerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/change")
    public ModelAndView getForm() {
        ModelAndView mnv = new ModelAndView("user/password");
        mnv.addObject("passwordForm", userService.prepareForm());
        return mnv;
    }

    @PostMapping("/change")
    public String changePassword(@ModelAttribute PasswordChangerForm pwd) {
        if (userService.changePassword(pwd)) {
            return "redirect:/logout";
        } else {
            return "redirect:/password/change";
        }
    }
}
