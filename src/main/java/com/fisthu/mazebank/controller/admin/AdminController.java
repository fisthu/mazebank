package com.fisthu.mazebank.controller.admin;

import com.fisthu.mazebank.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    public BorderPane adminParent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.INSTANCE.getViewFactory().getAdminSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case CLIENTS -> {
                    try {
                        adminParent.setCenter(Model.INSTANCE.getViewFactory().getAdminClientsView());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case DEPOSIT -> {
                    // TODO
                }
                default -> {
                    try {
                        adminParent.setCenter(Model.INSTANCE.getViewFactory().getCreateClientView());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
