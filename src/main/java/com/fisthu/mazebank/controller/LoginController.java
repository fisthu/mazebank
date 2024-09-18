package com.fisthu.mazebank.controller;

import com.fisthu.mazebank.model.Model;
import com.fisthu.mazebank.view.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public ChoiceBox<AccountType> accountSelector;
    public Label payeeAddressLbl;
    public TextField payeeAddressField;
    public PasswordField passwordField;
    public Button loginBtn;
    public Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLabel.setText("");

        accountSelector.setItems(FXCollections.observableArrayList(AccountType.CLIENT, AccountType.ADMIN));
        accountSelector.setValue(Model.INSTANCE.getViewFactory().getLoginAccountType());
        accountSelector.valueProperty().addListener(observable -> listenOnSelectorChange());

        loginBtn.setOnAction(actionEvent -> {
            try {
                loginAction();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void listenOnSelectorChange() {
        Model.INSTANCE.getViewFactory().setLoginAccountType(accountSelector.getValue());
        if (Model.INSTANCE.getViewFactory().getLoginAccountType() == AccountType.CLIENT) {
            payeeAddressLbl.setText("Payee Address::");
        } else {
            payeeAddressLbl.setText("Username:");
        }
    }

    private void closeLoggedStage() {
        Stage stage = (Stage) errorLabel.getScene().getWindow();
        Model.INSTANCE.getViewFactory().closeStage(stage);
    }

    private void loginAction() throws IOException {
        Model.INSTANCE.evaluateCred(payeeAddressField.getText(), passwordField.getText());
        if (Model.INSTANCE.isLoggedIn()) {
            closeLoggedStage();

            if (Model.INSTANCE.getViewFactory().getLoginAccountType() == AccountType.CLIENT) {
                Model.INSTANCE.getViewFactory().showClientWindow();
            } else {
                Model.INSTANCE.getViewFactory().showAdminWindow();
            }
        } else {
            payeeAddressField.setText("");
            passwordField.setText("");
            errorLabel.setText("Login failed");
        }
    }
}
