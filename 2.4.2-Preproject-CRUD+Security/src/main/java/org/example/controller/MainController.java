package org.example.controller;

import org.example.dto.UserRegistrationDTO;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String adminAccount(@ModelAttribute("user") UserRegistrationDTO userRegistrationDTO) {
        userService.admin(userRegistrationDTO);
        return "admin";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    //@GetMapping("/user/{name}")
    //    public ModelAndView userPage(@PathVariable("name") String name) {
    //        ModelAndView modelAndView = new ModelAndView("/user");
    //        modelAndView.addObject("user", userService.loadUserByUsername(name));
    //        return modelAndView;
    //    }


}
