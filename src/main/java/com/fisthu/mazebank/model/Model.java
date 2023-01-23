package com.fisthu.mazebank.model;

import com.fisthu.mazebank.view.AccountType;
import com.fisthu.mazebank.view.ViewFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public enum Model {
    INSTANCE;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private Client client;
    private boolean loggedIn;

    Model() {
        viewFactory = new ViewFactory();
        databaseDriver = new DatabaseDriver();

        // client data section
        client = new Client("", "", "", null, null, null);

        // admin data section
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void evaluateCred(String username, String password) {
        if (viewFactory.getLoginAccountType() == AccountType.CLIENT) {
            evaluateClientCred(username, password);
        } else {
            evaluateAdminCred(username, password);
        }
    }

    public void handleLogout(Stage currentStage) throws IOException {
        viewFactory.closeStage(currentStage);
        loggedIn = false;

        viewFactory.showLoginWindow();
    }

    private void evaluateClientCred(String payeeAddress, String password) {
        ResultSet clientData = databaseDriver.getClientData(payeeAddress, password);
        try {
            if (clientData != null && clientData.isBeforeFirst()) {
                client.firstnameProperty().set(clientData.getString("FirstName"));
                client.lastnameProperty().set(clientData.getString("LastName"));
                client.payeeAddressProperty().set(clientData.getString("PayeeAddress"));

                LocalDate date = getLocalDate(clientData.getString("Date"));
                client.dateCreatedProperty().set(date);
                loggedIn = true;
                // TODO


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void evaluateAdminCred(String username, String password) {
        ResultSet adminData = databaseDriver.getAdminData(username, password);

        try {
            if (adminData != null && adminData.isBeforeFirst()) {
                loggedIn = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private LocalDate getLocalDate(String dbDate) throws SQLException {
        String[] dates = dbDate.split("-");
        return LocalDate.of(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
    }
}
