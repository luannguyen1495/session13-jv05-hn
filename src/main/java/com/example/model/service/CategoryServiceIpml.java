package com.example.model.service;

import com.example.model.dao.CategoryDAO;
import com.example.model.dao.CategoryDAOImpl;
import com.example.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceIpml implements CategoryService{

    @Autowired
     private CategoryDAO categoryDAO;

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Boolean create(Category category) {
        return categoryDAO.create(category);
    }

    @Override
    public Boolean update(Category category, Integer integer) {
        return null;
    }

    @Override
    public Category findById(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
