package com.example.globalordermanagement.controller;

import com.example.globalordermanagement.model.entity.OrderFulfillment;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class OrderActionCellFactory implements Callback<TableColumn<OrderFulfillment, Void>, TableCell<OrderFulfillment, Void>> {

    @Override
    public TableCell<OrderFulfillment, Void> call(TableColumn<OrderFulfillment, Void> param) {
        return new TableCell<>() {
            private final Button viewDetailsButton = new Button("Xem chi tiết");

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    setGraphic(viewDetailsButton);
                    viewDetailsButton.setOnAction(event -> {
                        OrderFulfillment orderFulfillment = getTableView().getItems().get(getIndex());
                        showOrderDetails(orderFulfillment); // Gọi phương thức để hiển thị chi tiết
                    });
                }
            }
        };
    }

    private void showOrderDetails(OrderFulfillment orderFulfillment) {
        // Hiển thị chi tiết đơn hàng ở đây, bạn có thể mở một cửa sổ mới hoặc một dialog
        System.out.println("Hiển thị chi tiết cho đơn hàng: " + orderFulfillment.getOrderFulfillmentId());
        // Bạn có thể thêm mã để mở cửa sổ mới với thông tin chi tiết đơn hàng
    }
}
