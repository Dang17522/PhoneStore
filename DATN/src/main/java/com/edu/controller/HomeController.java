package com.edu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.service.OrderService;

@Controller
public class HomeController {
	@Autowired
	OrderService orderservice;
	
	@RequestMapping({"/","/home/index"})
	public String home() {
		return "redirect:/PhoneStore";
	}
	@RequestMapping({"/admin","/admin/home/index"})
	public String admin(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("orders", orderservice.findUsername(username));
		return "redirect:/assets/admin/index.html";
	}

    @RequestMapping("/PhoneStore/contact")
    public String contact() {
        return "layout/contact";
    }
    @RequestMapping("/PhoneStore/about")
    public String about() {
        return "layout/about";
    }
    @RequestMapping("/product/tip")
    public String tip() {
        return "hd/tip";
    }

    @RequestMapping("/product/note")
    public String tip2() {
        return "hd/tip2";
    }
}
