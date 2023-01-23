package com.fisthu.mazebank.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseDriver {
    private Connection connection;

    public DatabaseDriver() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:mazebank.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // client section

    // admin section

    // utility methods
}
