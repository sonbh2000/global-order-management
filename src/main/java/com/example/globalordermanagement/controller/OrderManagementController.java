package com.example.globalordermanagement.controller;

import com.example.globalordermanagement.dao.impl.OrderFulfillmentDAO;
import com.example.globalordermanagement.model.entity.OrderFulfillment;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrderManagementController {

    @FXML
    private TableView<OrderFulfillment> orderTable;

    @FXML
    private TableColumn<OrderFulfillment, Integer> orderIdColumn;

    @FXML
    private TableColumn<OrderFulfillment, String> orderDateColumn;

    @FXML
    private TableColumn<OrderFulfillment, String> orderStatusColumn;

    @FXML
    private TableColumn<OrderFulfillment, String> orderDetailsColumn;

    @FXML
    private TableColumn<OrderFulfillment, String> expectedDeliveryDateColumn;

    private final OrderFulfillmentDAO orderFulfillmentDAO = new OrderFulfillmentDAO();

    @FXML
    public void initialize() {
        loadOrderFulfillments();
    }

    private void loadOrderFulfillments() {
        ObservableList<OrderFulfillment> orderList = FXCollections.observableArrayList(orderFulfillmentDAO.read());

        // Set data to TableView
        orderTable.setItems(orderList);

        // Configure TableColumn to display data
        orderIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrderFulfillmentId()).asObject());
        orderDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDeliveryDate().toString()));
        orderStatusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus().name()));
//        orderDetailsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrderDetails())); // Assuming you have a method to get details
        expectedDeliveryDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExpectedDeliveryDate().toString()));
    }
}