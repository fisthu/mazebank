package com.fisthu.mazebank.controller.client;

import com.fisthu.mazebank.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    public BorderPane client_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Model.INSTANCE.getViewFactory().getClientSelectedMenuItemProperty().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case TRANSACTION -> {
                    try {
                        client_parent.setCenter(Model.INSTANCE.getViewFactory().getTransactionView());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case ACCOUNT -> {
                    try {
                        client_parent.setCenter(Model.INSTANCE.getViewFactory().getClientAccountView());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                default -> {
                    try {
                        client_parent.setCenter(Model.INSTANCE.getViewFactory().getDashboardView());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });


    }
}
