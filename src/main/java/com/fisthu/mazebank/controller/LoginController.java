package com.fisthu.mazebank.controller;

import com.fisthu.mazebank.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public ChoiceBox accountSelector;
    public Label payeeAddressLbl;
    public TextField payeeAddressField;
    public TextField passwordField;
    public Button loginBtn;
    public Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginBtn.setOnAction(actionEvent -> loginAction());
    }

    private void loginAction() {
        try {
            Stage stage = (Stage) errorLabel.getScene().getWindow();
            Model.INSTANCE.getViewFactory().closeStage(stage);

            Model.INSTANCE.getViewFactory().showClientWindow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
