package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    // Получить список всех пользователей

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "admin";
    }

    // Обновление данных о пользователе

    @GetMapping("/{id}/update")
    public ModelAndView edit(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("user", userService.findUserById(id));
        return modelAndView;
    }

    @PatchMapping("/{id}")
    public ModelAndView update(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("user", userService.update(user));
        return modelAndView;
    }

    // Удаление пользователя

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        userService.delete(id);
        return "admin";
    }
}
