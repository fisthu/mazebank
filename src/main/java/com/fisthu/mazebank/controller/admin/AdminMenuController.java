package com.fisthu.mazebank.controller.admin;

import com.fisthu.mazebank.model.Model;
import com.fisthu.mazebank.view.AdminMenuOption;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button createClientBtn;
    public Button clientsBtn;
    public Button depositBtn;
    public Button logoutBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    private void addListener() {
        createClientBtn.setOnAction(actionEvent -> onCreateClientEvent());
        clientsBtn.setOnAction(actionEvent -> onClientsEvent());
        depositBtn.setOnAction(actionEvent -> onDepositEvent());
    }

    private void onCreateClientEvent() {
        Model.INSTANCE.getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOption.CREATE_CLIENT);
    }

    private void onClientsEvent() {
        Model.INSTANCE.getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOption.CLIENTS);
    }

    private void onDepositEvent() {
        Model.INSTANCE.getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOption.DEPOSIT);
    }
}
