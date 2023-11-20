package com.example.controller;

import com.example.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;

@Controller

public class HomeController {
    @Autowired
    ServletContext context;

    @RequestMapping(value = {"/","/home"})
    public String home(){

        return "home";
    }
}
