package com.fisthu.mazebank.controller.client;

import com.fisthu.mazebank.model.MainMenu;
import com.fisthu.mazebank.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

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
    }

    private void onDashboardEvent() {
        Model.INSTANCE.getViewFactory().getClientSelectedMenuItemProperty().set(MainMenu.DASHBOARD.name());
    }

    private void onTransactionEvent() {
        Model.INSTANCE.getViewFactory().getClientSelectedMenuItemProperty().set(MainMenu.TRANSACTION.name());
    }
}
