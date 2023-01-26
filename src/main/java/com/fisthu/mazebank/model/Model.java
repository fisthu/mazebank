package com.fisthu.mazebank.model;

import com.fisthu.mazebank.view.AccountType;
import com.fisthu.mazebank.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public enum Model {
    INSTANCE;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private final Client client;
    private boolean loggedIn;

    private final ObservableList<Client> clients;

    private final ObservableList<Transaction> latestTransactions;
    private final ObservableList<Transaction> allTransactions;

    Model() {
        viewFactory = new ViewFactory();
        databaseDriver = new DatabaseDriver();

        // client data section
        client = new Client("", "", "", null, null, null);
        clients = FXCollections.observableArrayList();
        latestTransactions = FXCollections.observableArrayList();
        allTransactions = FXCollections.observableArrayList();

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

    public ObservableList<Client> getClients() {
        return clients;
    }

    public void setClients() {
        ResultSet allClients = databaseDriver.getAllClients();
        try {
            while (allClients.next()) {
                String firstName = allClients.getString("FirstName");
                String lastName = allClients.getString("LastName");
                String payeeAddress = allClients.getString("PayeeAddress");
                LocalDate date = getLocalDate(allClients.getString("Date"));

                CheckingAccount checkingAccount = getCheckingAccount(payeeAddress);
                SavingAccount savingAccount = getSavingAccount(payeeAddress);

                clients.add(new Client(firstName, lastName, payeeAddress, checkingAccount, savingAccount, date));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void evaluateClientCred(String payeeAddress, String password) {
        ResultSet clientData = databaseDriver.getClientData(payeeAddress, password);
        try {
            if (clientData.isBeforeFirst()) {
                client.firstnameProperty().set(clientData.getString("FirstName"));
                client.lastnameProperty().set(clientData.getString("LastName"));
                client.payeeAddressProperty().set(clientData.getString("PayeeAddress"));

                LocalDate date = getLocalDate(clientData.getString("Date"));
                client.dateCreatedProperty().set(date);

                CheckingAccount checkingAccount = getCheckingAccount(payeeAddress);
                SavingAccount savingAccount = getSavingAccount(payeeAddress);
                this.client.checkingAccountProperty().set(checkingAccount);
                this.client.savingAccountProperty().set(savingAccount);

                loggedIn = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void evaluateAdminCred(String username, String password) {
        ResultSet adminData = databaseDriver.getAdminData(username, password);

        try {
            if (adminData.isBeforeFirst()) {
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

    public CheckingAccount getCheckingAccount(String pAddress) {
        ResultSet checkingAccount = databaseDriver.getCheckingAccount(pAddress);
        try {
            String accountNumber = checkingAccount.getString("AccountNumber");
            int tLimit = (int) checkingAccount.getDouble("transactionLimit");
            var balance = checkingAccount.getDouble("Balance");
            return new CheckingAccount(pAddress, accountNumber, balance, tLimit);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public SavingAccount getSavingAccount(String pAddress) {
        ResultSet resultSet = databaseDriver.getSavingAccount(pAddress);
        try {
            String accountNumber = resultSet.getString("AccountNumber");
            double wLimit = resultSet.getDouble("withdrawalLimit");
            var balance = resultSet.getDouble("Balance");
            return new SavingAccount(pAddress, accountNumber, balance, wLimit);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Client> searchClients(String pAddress) {
        ObservableList<Client> result = FXCollections.observableArrayList();
        try (ResultSet resultSet = databaseDriver.searchClient(pAddress)) {
            if (resultSet.isBeforeFirst()) {
                CheckingAccount checkingAccount = getCheckingAccount(pAddress);
                SavingAccount savingAccount = getSavingAccount(pAddress);

                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                LocalDate date = getLocalDate(resultSet.getString("Date"));

                result.add(new Client(firstName, lastName, pAddress, checkingAccount, savingAccount, date));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public void setLatestTransactions() {
        prepareTransactions(latestTransactions, 4);
    }

    public ObservableList<Transaction> getLatestTransactions() {
        return latestTransactions;
    }

    public void setAllTransactions() {
        prepareTransactions(allTransactions, -1);
    }

    public ObservableList<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public void prepareTransactions(ObservableList<Transaction> transactions, int limit) {
        ResultSet resultSet = databaseDriver.getTransactions(client.payeeAddressProperty().get(), limit);

        try {
            while (resultSet.next()) {
                String sender = resultSet.getString("Sender");
                String receiver = resultSet.getString("Receiver");
                double amount = resultSet.getDouble("Amount");
                LocalDate date = getLocalDate(resultSet.getString("Date"));
                String msg = resultSet.getString("Message");

                transactions.add(new Transaction(sender, receiver, amount, date, msg));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
