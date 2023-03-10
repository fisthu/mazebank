package com.fisthu.mazebank.controller.client;

import com.fisthu.mazebank.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.fisthu.mazebank.view.ClientMenuOption.*;

public class ClientMenuController implements Initializable {
    public Button dashboardBtn;
    public Button transactionBtn;
    public Button accountBtn;
    public Button profileBtn;
    public Button logoutBtn;
    public Button reportBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    private void addListener() {
        dashboardBtn.setOnAction(actionEvent -> onDashboardEvent());
        transactionBtn.setOnAction(actionEvent -> onTransactionEvent());
        accountBtn.setOnAction(actionEvent -> onAccountEvent());
        logoutBtn.setOnAction(event -> onLogout());
    }

    private void onDashboardEvent() {
        Model.INSTANCE.getViewFactory().getClientSelectedMenuItemProperty().set(DASHBOARD);
    }

    private void onTransactionEvent() {
        Model.INSTANCE.getViewFactory().getClientSelectedMenuItemProperty().set(TRANSACTION);
    }

    private void onAccountEvent() {
        Model.INSTANCE.getViewFactory().getClientSelectedMenuItemProperty().set(ACCOUNT);
    }

    private void onLogout() {
        Stage stage = (Stage) dashboardBtn.getScene().getWindow();
        try {
            Model.INSTANCE.handleLogout(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
