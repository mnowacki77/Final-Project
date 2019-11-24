package com.sda.tasklist.controller.admin;

import com.sda.tasklist.service.admin.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public ModelAndView getUsers() {
        ModelAndView mnv = new ModelAndView("admin/users");
        mnv.addObject("users", adminService.getUsers());
        return mnv;
    }
}
