package com.fisthu.mazebank.view;

import com.fisthu.mazebank.controller.admin.AdminController;
import com.fisthu.mazebank.controller.client.ClientController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    // Client View
    private AnchorPane dashboardView;
    private AnchorPane transactionView;
    private final StringProperty clientSelectedMenuItem;
    private AnchorPane clientAccountView;
    private final StringProperty adminSelectedMenuItem;
    private AnchorPane createClientView;

    public ViewFactory() {
        this.clientSelectedMenuItem = new SimpleStringProperty("");
        this.adminSelectedMenuItem = new SimpleStringProperty("");
    }

    public StringProperty getClientSelectedMenuItemProperty() {
        return clientSelectedMenuItem;
    }

    public AnchorPane getDashboardView() throws IOException {
        if (dashboardView == null) {
            dashboardView = new FXMLLoader(getClass().getResource("/fxml/client/Dashboard.fxml")).load();
        }

        return dashboardView;
    }

    public AnchorPane getTransactionView() throws IOException {
        if (transactionView == null) {
            transactionView = new FXMLLoader(getClass().getResource("/fxml/client/transaction.fxml")).load();
        }

        return transactionView;
    }

    public AnchorPane getClientAccountView() throws IOException {
        if (clientAccountView == null) {
            clientAccountView = new FXMLLoader(getClass().getResource("/fxml/client/Account.fxml")).load();
        }

        return clientAccountView;
    }

    public StringProperty getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    public AnchorPane getCreateClientView() throws IOException {
        if (createClientView == null) {
            createClientView = new FXMLLoader(getClass().getResource("/fxml/admin/CreateClient.fxml")).load();
        }

        return createClientView;
    }

    public void showLoginWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        buildStage(fxmlLoader);
    }

    public void showClientWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/client/Client.fxml"));
        fxmlLoader.setController(new ClientController());

        buildStage(fxmlLoader);
    }

    public void showAdminWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/admin/Admin.fxml"));
        AdminController adminController = new AdminController();
        fxmlLoader.setController(adminController);

        buildStage(fxmlLoader);
    }

    public void closeStage(Stage stage) {
        stage.close();
    }

    private void buildStage(FXMLLoader fxmlLoader) throws IOException {
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Maze Bank");
        stage.show();
    }
}
