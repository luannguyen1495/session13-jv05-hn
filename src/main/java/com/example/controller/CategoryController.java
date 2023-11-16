package com.example.controller;

import com.example.model.entity.Category;
import com.example.model.service.CategoryService;
import com.example.model.service.CategoryServiceIpml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/category")
    public String index(Model model){
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories",categories);
        return "category/index";
    }

    @RequestMapping("/add-category")
    public String add(Model model){
        Category category = new Category();
        model.addAttribute("category",category);
        return "category/add";
    }
    @RequestMapping("/create-category")
    public String create(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes){
        Boolean check =  categoryService.create(category);
        if(!check){
            return "category/add?err=";
        }
        redirectAttributes.addFlashAttribute("mess","Thêm mới thành công");
        return "redirect:/category";
    }

    @RequestMapping("/edit-category/{id}")
    public String edit(@PathVariable("id") Integer id){
        System.out.println(id);
        return "category/edit";
    }

}
