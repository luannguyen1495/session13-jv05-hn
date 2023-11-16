package com.example.model.dao;

import com.example.model.entity.Category;
import com.example.model.entity.Product;
import com.example.util.ConnectionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImpl implements  ProductDAO {
    @Autowired
    private CategoryDAO categoryDAO;
    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        Connection connection = null;
        connection = ConnectionDB.openConnection();

        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM product");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setNameProduct(rs.getString("name_product"));
                product.setPrice(rs.getDouble("price"));
                Category category = categoryDAO.findById(rs.getInt("category_id"));
                product.setCategory(category);
                list.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return list;
    }

    @Override
    public Boolean create(Product product) {
        return null;
    }

    @Override
    public Boolean update(Product product, Integer integer) {
        return null;
    }

    @Override
    public Product findById(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
