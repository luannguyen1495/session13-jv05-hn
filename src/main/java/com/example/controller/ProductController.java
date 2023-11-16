package com.example.controller;

import com.example.model.entity.Product;
import com.example.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping("/product")
    public String index(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products",products);
        return "product/index";
    }
}
