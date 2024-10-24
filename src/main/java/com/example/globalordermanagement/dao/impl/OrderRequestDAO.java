package com.example.globalordermanagement.dao.impl;

import com.example.globalordermanagement.config.DatabaseConnection;
import com.example.globalordermanagement.dao.Dao;
import com.example.globalordermanagement.model.entity.OrderRequest;
import com.example.globalordermanagement.model.enums.OrderRequestStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderRequestDAO implements Dao<OrderRequest> {
    public void create(OrderRequest orderRequest) {
        String sql = "INSERT INTO Order_Request (desired_delivery_date, status) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(orderRequest.getDesiredDeliveryDate()));
            statement.setString(2, orderRequest.getStatus().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OrderRequest> read() {
        List<OrderRequest> orders = new ArrayList<>();
        String sql = "SELECT * FROM Order_Request";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("order_id");
                LocalDate desiredDeliveryDate = resultSet.getDate("desired_delivery_date").toLocalDate();
                String status = resultSet.getString("status");
                orders.add(new OrderRequest(id, desiredDeliveryDate, OrderRequestStatus.valueOf(status)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public void update(OrderRequest orderRequest) {
        String sql = "UPDATE Order_Request SET desired_delivery_date = ?, status = ? WHERE order_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(orderRequest.getDesiredDeliveryDate()));
            statement.setString(2, orderRequest.getStatus().name());
            statement.setInt(3, orderRequest.getOrderId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int orderId) {
        String sql = "DELETE FROM Order_Request WHERE order_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

