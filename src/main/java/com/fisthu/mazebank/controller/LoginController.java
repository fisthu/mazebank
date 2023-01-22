package com.fisthu.mazebank.controller;

import com.fisthu.mazebank.model.Model;
import com.fisthu.mazebank.view.AccountType;
import javafx.collections.FXCollections;
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

    public ChoiceBox<AccountType> accountSelector;
    public Label payeeAddressLbl;
    public TextField payeeAddressField;
    public TextField passwordField;
    public Button loginBtn;
    public Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountSelector.setItems(FXCollections.observableArrayList(AccountType.CLIENT, AccountType.ADMIN));
        accountSelector.setValue(Model.INSTANCE.getViewFactory().getLoginAccountType());
        accountSelector.valueProperty().addListener(observable -> Model.INSTANCE.getViewFactory().setLoginAccountType(accountSelector.getValue()));

        loginBtn.setOnAction(actionEvent -> loginAction());
    }

    private void loginAction() {
        try {
            Stage stage = (Stage) errorLabel.getScene().getWindow();
            Model.INSTANCE.getViewFactory().closeStage(stage);

            if (Model.INSTANCE.getViewFactory().getLoginAccountType() == AccountType.CLIENT) {
                Model.INSTANCE.getViewFactory().showClientWindow();
            } else {
                Model.INSTANCE.getViewFactory().showAdminWindow();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
