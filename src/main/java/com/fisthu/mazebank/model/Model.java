package com.fisthu.mazebank.model;

import com.fisthu.mazebank.view.AccountType;
import com.fisthu.mazebank.view.ViewFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public enum Model {
    INSTANCE;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private AccountType loginAccountType = AccountType.CLIENT;

    private Client client;

    private boolean clientLoggedIn;

    Model() {
        viewFactory = new ViewFactory();
        databaseDriver = new DatabaseDriver();

        // client data section
        clientLoggedIn = false;
        client = new Client("", "", "", null, null, null);

        // admin data section
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    public boolean isClientLoggedIn() {
        return clientLoggedIn;
    }

    public void setClientLoggedIn(boolean flag) {
        clientLoggedIn = flag;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void evaluateClientCred(String payeeAddress, String password) {
        ResultSet clientData = databaseDriver.getClientData(payeeAddress, password);
        try {
            if (clientData != null && clientData.isBeforeFirst()) {
                client.firstnameProperty().set(clientData.getString("FirstName"));
                client.lastnameProperty().set(clientData.getString("LastName"));
                client.payeeAddressProperty().set(clientData.getString("PayeeAddress"));

                LocalDate date = getLocalDate(clientData.getString("Date"));
                client.dateCreatedProperty().set(date);
                clientLoggedIn = true;
                // TODO


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
