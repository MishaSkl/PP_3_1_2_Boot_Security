package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;



@Controller
@RequestMapping("admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

//    @GetMapping()
//    public String showAdminPage(Model model, Principal principal) {
//        User userTest = userService.getUserByUsername(principal.getName());
//        model.addAttribute("users", userService.getAllUsers());
//        model.addAttribute("roles", roleService.getRoles());
//        model.addAttribute("login", userTest);
//        return "adminPage";
//    }
//
//    @PutMapping("/{id}/update")
//    public String update(@ModelAttribute("user") User user,
//                         @PathVariable("id") Long id) {
//        userService.updateUser(id, user);
//        return "redirect:/admin";
//    }
//
//    @DeleteMapping("/{id}/delete")
//    public String delete(@PathVariable("id") Long id) {
//        userService.deleteUserById(id);
//        return "redirect:/admin";
//    }
//
//    @PostMapping()
//    public String create(@ModelAttribute("user") User user) {
//        userService.saveUser(user);
//        return "redirect:/admin";
//    }


    @GetMapping()
    public String showAdminGeneralPage(Principal principal, Model model) {
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("admin", userService.getUserById(user.getId()));
        model.addAttribute("listOfUsers", userService.getAllUsers());
        model.addAttribute("personalRole", user.convertSetOfRoleToString(userService.getUserById(user.getId()).getRoles()));
        model.addAttribute("roles", roleService.getRoles());
        return "viewAdmin/adminPage";
    }

    @GetMapping("/personalPage")
    public String showAdminPersonalPage(Principal principal, Model model) {
        User userTest = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", userTest);
        model.addAttribute("login", userTest);
        model.addAttribute("role", userTest.convertSetOfRoleToString(userTest.getRoles()));
        return "/user";
    }

}
