package com.fisthu.mazebank.model;

import java.sql.*;

public class DatabaseDriver {
    private final Connection connection;

    public DatabaseDriver() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:mazebank.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // client section

    public ResultSet getClientData(String pAddress, String password) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery("select * from Clients where PayeeAddress = '" + pAddress + "' and Password='" + password + "'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // admin section
    public ResultSet getAdminData(String username, String password) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery("select * from Admins where Username = '" + username + "' and Password='" + password + "'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // utility methods
}
