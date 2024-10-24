package com.example.globalordermanagement.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/global_order_management?allowPublicKeyRetrieval=true&useSSL=false"; // Thay đổi tên cơ sở dữ liệu
    private static final String USER = "root"; // Tên đăng nhập
    private static final String PASSWORD = "root"; // Mật khẩu

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
