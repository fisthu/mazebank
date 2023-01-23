package com.fisthu.mazebank.model;

import java.sql.*;
import java.time.LocalDate;

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
            return statement.executeQuery("select * from Clients where PayeeAddress = '%s' and Password='%s'".formatted(pAddress, password));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // admin section
    public ResultSet getAdminData(String username, String password) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery("select * from Admins where Username = '%s' and Password='%s'".formatted(username, password));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createClient(String fName, String lName, String pAddress, String passwd) {
        try {
            Statement statement = connection.createStatement();
            String createdDate = LocalDate.now().toString();
            String query = "insert into Clients (FirstName, LastName, PayeeAddress, Password, Date) values ('%s', '%s', '%s', '%s', '%s')".formatted(fName, lName, pAddress, passwd, createdDate);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createCheckingAccount(String owner, String number, double transLimit, double balance) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into CheckingAccounts(Owner, AccountNumber, TransactionLimit, Balance) VALUES ('%s', '%s', %f, %f)".formatted(owner, number, transLimit, balance));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createSavingAccount(String owner, String number, double wLimit, double balance) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into SavingsAccounts(Owner, AccountNumber, WithdrawalLimit, Balance) VALUES ('%s', '%s', %f, %f)".formatted(owner, number, wLimit, balance));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // utility methods
    public int getLastClientId() {
        try {
            Statement statement = connection.createStatement();
            ResultSet clients = statement.executeQuery("select * from sqlite_sequence where name = '%s'".formatted("Clients"));
            return clients.getInt("seq");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
