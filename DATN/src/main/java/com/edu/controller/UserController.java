package com.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.service.OrderService;
import com.edu.service.UserService;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    
    @Autowired
    OrderService orderService;
    
    @RequestMapping("/admin/user")
    public String list(Model model) {
        model.addAttribute("user", userService.findAll());
        return "user/list";
    }
    @RequestMapping("admin/user/detail/{username}")
    public String detail(Model model,@PathVariable("username") String username) {
        model.addAttribute("user", userService.findID(username));
        model.addAttribute("order", orderService.findUsername(username));
        return "user/detail";
    }
}
