package com.example.controller;

import com.example.model.entity.Category;
import com.example.model.entity.Product;
import com.example.model.service.CategoryService;
import com.example.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/product")
    public String index(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products",products);
        return "product/index";
    }
    @RequestMapping("/add-product")
    public String add(Model model){
        Product product = new Product();
        model.addAttribute("product",product);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories",categories);

        return "product/add";
    }

    @RequestMapping("/create-product")
    public String create(@ModelAttribute("product") Product product, @RequestParam("img_upload") MultipartFile file, HttpServletRequest request){
        // xư ly upload file
        String path = request.getServletContext().getRealPath("uploads/images");
        String fileName = file.getOriginalFilename();
        File destination = new File(path+"/"+fileName);
        try {
            Files.write(destination.toPath(), file.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        product.setImage(fileName);
        System.out.println(fileName);
        productService.create(product);
        return "redirect:/product";
    }

    @RequestMapping("edit-product/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){
        Product product = productService.findById(id);
        System.out.println(product.getNameProduct());
//        Product product = new Product();
        model.addAttribute("product",product);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories",categories);
        return "product/edit";
    }

    @RequestMapping("update-product/{id}")
    public String update(@PathVariable("id") Integer id , @ModelAttribute("product") Product product,@RequestParam("img_upload") MultipartFile file, HttpServletRequest request){
        // xử lý upload ảnh
        if(file != null && !file.isEmpty()){
            // xư ly upload file
            String path = request.getServletContext().getRealPath("uploads/images");
            String fileName = file.getOriginalFilename();
            File destination = new File(path+"/"+fileName);
            try {
                Files.write(destination.toPath(), file.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            product.setImage(fileName);
        }
        productService.update(product,id);

        return "redirect:/product";
    }
}

