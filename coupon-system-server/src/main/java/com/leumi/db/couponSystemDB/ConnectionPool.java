package com.leumi.db.couponSystemDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    private static ConnectionPool INSTANCE;
    private Connection connection;
    private static final String DOMAIN_STRING = "localhost";// 127.0.0.1
    private static final String DB_NAME = "coupon_system";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "1234";
    private static final String CONNECTION_STRING = "jdbc:mysql://" + DOMAIN_STRING + "/" + DB_NAME + "?user="
            + USER_NAME + "&password=" + PASSWORD;

    private ConnectionPool() {
        try {
            createConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ConnectionPool();
        }
        return INSTANCE;
    }

    private void createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(CONNECTION_STRING);
        System.out.println("connection successful!");
    }

    public Connection getConnection(){
        return connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
