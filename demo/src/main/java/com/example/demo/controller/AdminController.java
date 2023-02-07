package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAdminPanel(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "panel_admin";
    }

    @DeleteMapping("delete/{id}")
    public String deleteUserFromDb(@PathVariable("id") Long id) {
        userService.removeById(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "add")
    public String getAddUserPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "add_user";
    }

    @PostMapping(value = "add")
    public String saveNewUserToDb(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "edit/{id}")
    public String getEditUserPage(Model model, @PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit_user";
    }

    @PostMapping(value = "edit/{id}")
    public String saveEditedUserToDb(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }
}
