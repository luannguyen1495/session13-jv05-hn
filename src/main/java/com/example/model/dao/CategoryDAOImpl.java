package com.example.model.dao;

import com.example.model.entity.Category;
import com.example.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class CategoryDAOImpl implements CategoryDAO{
    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        Connection connection = null;
        connection = ConnectionDB.openConnection();

        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM category");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setStatus(rs.getBoolean("status"));
                list.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return list;
    }

    @Override
    public Boolean create(Category category) {

        Connection connection = null;
        connection = ConnectionDB.openConnection();

        try {
            String sql = "INSERT INTO category(name,status) VALUES (?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,category.getName());
            pstm.setBoolean(2,category.getStatus());
            int rs = pstm.executeUpdate();
            if (rs > 0)
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public Boolean update(Category category, Integer integer) {
        return null;
    }

    @Override
    public Category findById(Integer integer) {
        Category category = new Category();
        Connection connection = null;
        connection = ConnectionDB.openConnection();

        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM category WHERE id=?");
            pstm.setInt(1,integer);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){

                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setStatus(rs.getBoolean("status"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return category;
    }

    @Override
    public void delete(Integer integer) {

    }
}
