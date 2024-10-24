package com.example.globalordermanagement.dao.impl;

import com.example.globalordermanagement.config.DatabaseConnection;
import com.example.globalordermanagement.dao.Dao;
import com.example.globalordermanagement.model.entity.SiteProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SiteProductDAO implements Dao<SiteProduct> {
    public void create(SiteProduct siteProduct) {
        String sql = "INSERT INTO Site_Product (site_id, product_id, available_quantity, delivery_days) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, siteProduct.getSiteId());
            statement.setInt(2, siteProduct.getProductId());
            statement.setInt(3, siteProduct.getAvailableQuantity());
            statement.setInt(4, siteProduct.getDeliveryDays());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SiteProduct> read() {
        List<SiteProduct> siteProducts = new ArrayList<>();
        String sql = "SELECT * FROM Site_Product";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("site_product_id");
                int siteId = resultSet.getInt("site_id");
                int productId = resultSet.getInt("product_id");
                int availableQuantity = resultSet.getInt("available_quantity");
                int deliveryDays = resultSet.getInt("delivery_days");
                siteProducts.add(new SiteProduct(id, siteId, productId, availableQuantity, deliveryDays));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return siteProducts;
    }

    public SiteProduct getById(int siteProductId) {
        String sql = "SELECT * FROM Site_Product WHERE site_product_id = ?";
        SiteProduct siteProduct = null; // Khởi tạo biến để lưu sản phẩm tìm thấy

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Thiết lập giá trị cho tham số trong truy vấn
            preparedStatement.setInt(1, siteProductId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Kiểm tra xem có kết quả không
                if (resultSet.next()) {
                    int id = resultSet.getInt("site_product_id");
                    int siteId = resultSet.getInt("site_id");
                    int productId = resultSet.getInt("product_id");
                    int availableQuantity = resultSet.getInt("available_quantity");
                    int deliveryDays = resultSet.getInt("delivery_days");

                    // Tạo đối tượng SiteProduct từ kết quả
                    siteProduct = new SiteProduct(id, siteId, productId, availableQuantity, deliveryDays);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return siteProduct; // Trả về sản phẩm tìm thấy, hoặc null nếu không tìm thấy
    }

    public void update(SiteProduct siteProduct) {
        String sql = "UPDATE Site_Product SET site_id = ?, product_id = ?, available_quantity = ?, delivery_days = ? WHERE site_product_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, siteProduct.getSiteId());
            statement.setInt(2, siteProduct.getProductId());
            statement.setInt(3, siteProduct.getAvailableQuantity());
            statement.setInt(4, siteProduct.getDeliveryDays());
            statement.setInt(5, siteProduct.getSiteProductId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int siteProductId) {
        String sql = "DELETE FROM Site_Product WHERE site_product_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, siteProductId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

