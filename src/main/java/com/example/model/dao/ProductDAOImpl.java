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
                product.setImage(rs.getString("image"));
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
        Boolean isCheck = false ;
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product (name_product,price,category_id,image ) VALUES (?,?,?,?)");
            preparedStatement.setString(1,product.getNameProduct());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setInt(3,product.getCategory().getId());
            preparedStatement.setString(4,product.getImage());
            int check = preparedStatement.executeUpdate();
            if ( check > 0 ) {
                isCheck = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return isCheck;
    }

    @Override
    public Boolean update(Product product, Integer integer) {

        Boolean isCheck = false ;
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            String sql = "UPDATE product SET name_product =?, price = ?,image=?,category_id=? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,product.getNameProduct());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setString(3,product.getImage());
            preparedStatement.setInt(4,product.getCategory().getId());
            preparedStatement.setInt(5,integer);
            int check = preparedStatement.executeUpdate();
            if ( check > 0 ) {
                isCheck = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return isCheck;
    }

    @Override
    public Product findById(Integer integer) {

        Product product = new Product();
        Connection connection = null;
        connection = ConnectionDB.openConnection();

        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM product WHERE id=?");
            pstm.setInt(1,integer);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                product.setId(rs.getInt("id"));
                product.setNameProduct(rs.getString("name_product"));
                product.setPrice(rs.getDouble("price"));
                product.setImage(rs.getString("image"));
                Category category = categoryDAO.findById(rs.getInt("category_id"));
                product.setCategory(category);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }

        return product;

    }

    @Override
    public void delete(Integer integer) {

    }
}
