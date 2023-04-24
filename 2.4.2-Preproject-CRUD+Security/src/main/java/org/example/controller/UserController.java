package org.example.controller;

import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping
    @ResponseBody
    public ModelAndView currentUserNameSimple(Authentication authentication) {
        ModelAndView mav = new ModelAndView("user");
        mav.addObject("user", userService.loadUserByUsername(authentication.getName()));
        return mav;
    }
}
