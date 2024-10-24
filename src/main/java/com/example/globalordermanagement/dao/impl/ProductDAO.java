package com.example.globalordermanagement.dao.impl;

import com.example.globalordermanagement.config.DatabaseConnection;
import com.example.globalordermanagement.dao.Dao;
import com.example.globalordermanagement.model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Dao<Product> {
    public void create(Product product) {
        String sql = "INSERT INTO Product (product_name) VALUES (?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getProductName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> read() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("product_id");
                String name = resultSet.getString("product_name");
                products.add(new Product(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product getById(int productId) {
        String sql = "SELECT * FROM Product WHERE product_id = ?";
        Product product = null; // Khởi tạo biến để lưu sản phẩm tìm thấy

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Thiết lập giá trị cho tham số trong truy vấn
            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Kiểm tra xem có kết quả không
                if (resultSet.next()) {
                    int id = resultSet.getInt("product_id");
                    String name = resultSet.getString("product_name");

                    // Tạo đối tượng Product từ kết quả
                    product = new Product(id, name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product; // Trả về sản phẩm tìm thấy, hoặc null nếu không tìm thấy
    }

    public void update(Product product) {
        String sql = "UPDATE Product SET product_name = ? WHERE product_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getProductId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int productId) {
        String sql = "DELETE FROM Product WHERE product_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

