package com.fisthu.mazebank.controller.admin;

import com.fisthu.mazebank.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    public BorderPane adminParent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.INSTANCE.getViewFactory().getAdminSelectedMenuItem().addListener((observableValue, newVal, oldVal) -> {
           // add switch statement
        });
    }
}
