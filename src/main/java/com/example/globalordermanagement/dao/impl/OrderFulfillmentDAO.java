package com.example.globalordermanagement.dao.impl;

import com.example.globalordermanagement.config.DatabaseConnection;
import com.example.globalordermanagement.dao.Dao;
import com.example.globalordermanagement.model.entity.OrderFulfillment;
import com.example.globalordermanagement.model.enums.OrderFulfillmentStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderFulfillmentDAO implements Dao<OrderFulfillment> {
    public void create(OrderFulfillment orderFulfillment) {
        String sql = "INSERT INTO Order_Fulfillment (order_id, site_product_id, fulfillment_status) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderFulfillment.getOrderId());
            statement.setInt(2, orderFulfillment.getSiteProductId());
            statement.setString(3, orderFulfillment.getStatus().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OrderFulfillment> read() {
        List<OrderFulfillment> fulfillments = new ArrayList<>();
        String sql = "SELECT * FROM Order_Fulfillment";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("order_fulfillment_id");
                int orderId = resultSet.getInt("order_id");
                int siteProductId = resultSet.getInt("site_product_id");
                int fulfilledQuantity = resultSet.getInt("fulfilled_quantity");
                LocalDate expectedDeliveryDate = resultSet.getDate("expected_delivery_date").toLocalDate();
                LocalDate deliveryDate = resultSet.getDate("delivery_date").toLocalDate();
                String status = resultSet.getString("status");
                fulfillments.add(new OrderFulfillment(
                        id,
                        orderId,
                        siteProductId,
                        fulfilledQuantity,
                        expectedDeliveryDate,
                        deliveryDate,
                        OrderFulfillmentStatus.valueOf(status)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fulfillments;
    }

    public void update(OrderFulfillment orderFulfillment) {
        String sql = "UPDATE Order_Fulfillment SET order_id = ?, site_product_id = ?, fulfillment_status = ? WHERE fulfillment_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderFulfillment.getOrderId());
            statement.setInt(2, orderFulfillment.getSiteProductId());
            statement.setString(3, orderFulfillment.getStatus().name());
            statement.setInt(4, orderFulfillment.getOrderFulfillmentId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int fulfillmentId) {
        String sql = "DELETE FROM Order_Fulfillment WHERE fulfillment_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, fulfillmentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
