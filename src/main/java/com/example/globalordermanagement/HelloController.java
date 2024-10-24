package com.example.globalordermanagement;

import com.example.globalordermanagement.dao.impl.OrderFulfillmentDAO;
import com.example.globalordermanagement.dao.impl.ProductDAO;
import com.example.globalordermanagement.dao.impl.SiteProductDAO;
import com.example.globalordermanagement.model.entity.OrderFulfillment;
import com.example.globalordermanagement.model.entity.Product;
import com.example.globalordermanagement.model.entity.SiteProduct;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
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

    private final SiteProductDAO siteProductDAO = new SiteProductDAO();

    private final ProductDAO productDAO = new ProductDAO();

    @FXML
    public void initialize() {
        loadOrderFulfillments();

        orderDetailsColumn.setCellFactory(column -> new TableCell<OrderFulfillment, String>() {
            private final Button viewDetailsButton = new Button("Xem chi tiết");

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    setGraphic(viewDetailsButton);
                    viewDetailsButton.setOnAction(event -> handleViewDetails(getTableRow().getItem()));
                }
            }
        });
    }

    // Phương thức xử lý sự kiện
    @FXML
    private void handleViewDetails(OrderFulfillment order) {
        if (order != null) {
            Integer siteProductId = order.getSiteProductId();
            SiteProduct siteProduct = getSiteProductById(siteProductId); // Lấy thông tin SiteProduct
            Product product = getProductById(siteProduct.getProductId()); // Lấy thông tin SiteProduct

            if (siteProduct != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Chi tiết sản phẩm");
                alert.setHeaderText("Thông tin chi tiết cho sản phẩm: " + product.getProductName());
                alert.setContentText("Mã sản phẩm: " + product.getProductId() + "\n" +
                        "Số lượng: " + siteProduct.getAvailableQuantity() + "\n" +
                        "day: " + siteProduct.getDeliveryDays());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Không tìm thấy sản phẩm");
                alert.setHeaderText(null);
                alert.setContentText("Không tìm thấy thông tin sản phẩm với mã: " + siteProductId);
                alert.showAndWait();
            }
        }
    }

    // Phương thức giả lập để lấy thông tin SiteProduct từ cơ sở dữ liệu
    private SiteProduct getSiteProductById(Integer siteProductId) {
        return siteProductDAO.getById(siteProductId);
    }

    private Product getProductById(Integer productId) {
        return productDAO.getById(productId);
    }

    private void loadOrderFulfillments() {
        ObservableList<OrderFulfillment> orderList = FXCollections.observableArrayList(orderFulfillmentDAO.read());

        // Set data to TableView
        orderTable.setItems(orderList);

        // Configure TableColumn to display data
        orderIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrderFulfillmentId()).asObject());
        orderDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDeliveryDate().toString()));
        orderStatusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus().name()));
        orderDetailsColumn.setCellValueFactory(cellData -> new SimpleStringProperty("Xem chi tiết")); // Assuming you have a method to get details
        expectedDeliveryDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExpectedDeliveryDate().toString()));
    }
}