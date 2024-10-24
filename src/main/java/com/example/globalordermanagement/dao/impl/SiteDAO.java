package com.example.globalordermanagement.dao.impl;

import com.example.globalordermanagement.config.DatabaseConnection;
import com.example.globalordermanagement.dao.Dao;
import com.example.globalordermanagement.model.entity.Site;
import com.example.globalordermanagement.model.enums.ShippingMethod;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SiteDAO implements Dao<Site> {
    public void create(Site site) {
        String sql = "INSERT INTO Site (site_name, location, shipping_method, warehouse_stock) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, site.getSiteName());
            statement.setString(2, site.getLocation());
            statement.setString(3, site.getShippingMethod().name());
            statement.setInt(4, site.getWarehouseStock());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Site> read() {
        List<Site> sites = new ArrayList<>();
        String sql = "SELECT * FROM Site";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("site_id");
                String name = resultSet.getString("site_name");
                String location = resultSet.getString("location");
                String shippingMethod = resultSet.getString("shipping_method");
                int warehouseStock = resultSet.getInt("warehouse_stock");
                sites.add(new Site(id, name, location, ShippingMethod.valueOf(shippingMethod), warehouseStock));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sites;
    }

    public void update(Site site) {
        String sql = "UPDATE Site SET site_name = ?, location = ?, shipping_method = ?, warehouse_stock = ? WHERE site_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, site.getSiteName());
            statement.setString(2, site.getLocation());
            statement.setString(3, String.valueOf(site.getShippingMethod()));
            statement.setInt(4, site.getWarehouseStock());
            statement.setInt(5, site.getSiteId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int siteId) {
        String sql = "DELETE FROM Site WHERE site_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, siteId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
