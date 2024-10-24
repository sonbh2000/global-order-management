package com.example.globalordermanagement.dao.impl;

import com.example.globalordermanagement.config.DatabaseConnection;
import com.example.globalordermanagement.dao.Dao;
import com.example.globalordermanagement.model.entity.OrderError;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderErrorDAO implements Dao<OrderError> {
    public void create(OrderError orderError) {
        String sql = "INSERT INTO Order_Error (error_message, order_id) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, orderError.getErrorMessage());
            statement.setInt(2, orderError.getOrderId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OrderError> read() {
        List<OrderError> orderErrors = new ArrayList<>();
        String sql = "SELECT * FROM Order_Error";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("error_id");
                String errorMessage = resultSet.getString("error_message");
                int orderId = resultSet.getInt("order_id");
                orderErrors.add(new OrderError(id, orderId, errorMessage));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderErrors;
    }

    public void update(OrderError orderError) {
        String sql = "UPDATE Order_Error SET error_message = ?, order_id = ? WHERE error_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, orderError.getErrorMessage());
            statement.setInt(2, orderError.getOrderId());
            statement.setInt(3, orderError.getErrorId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int errorId) {
        String sql = "DELETE FROM Order_Error WHERE error_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, errorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

