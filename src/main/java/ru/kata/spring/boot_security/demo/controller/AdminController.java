package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    @Autowired

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("")
    public String showAllUser(ModelMap model) {
        List<User> messages = userService.getAllUser();
        model.addAttribute("messages", messages);
        return "admin";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(ModelMap model) {

        model.addAttribute("messages", new User());

        model.addAttribute("roles", roleService.getAllRole());


        return "userInfo";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("messages") User user) {

        userService.addUser(user);

        return "redirect:/admin";
    }

    @DeleteMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";

    }

    @GetMapping("/user-update/{id}")
    public String updateUser(@PathVariable("id") Long id, ModelMap model) {
        User messages = userService.findUserById(id);
        model.addAttribute("messages", messages);
        List<Role> roles = roleService.getAllRole();
        model.addAttribute("roles", roles);
        return "userInfo";
    }

}