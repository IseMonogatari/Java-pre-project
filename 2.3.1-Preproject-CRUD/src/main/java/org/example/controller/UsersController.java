package org.example.controller;

import org.example.model.User;
import org.example.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ModelAndView allUsers() {
        ModelAndView modelAndView = new ModelAndView("users/allUsers");
        modelAndView.addObject("users", usersService.readAll());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getUserById(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("users/getUserById");
        modelAndView.addObject("user", usersService.getUserById(id));
        return modelAndView;
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public ModelAndView saveUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView("redirect:/users");
        modelAndView.addObject("user", usersService.create(user));
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("users/edit");
        modelAndView.addObject("user", usersService.getUserById(id));
        return modelAndView;
    }

    @PatchMapping("/{id}")
    public ModelAndView update(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView("redirect:/users");
        modelAndView.addObject("user", usersService.update(user));
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        usersService.delete(id);
        return "redirect:/users";
    }
}
