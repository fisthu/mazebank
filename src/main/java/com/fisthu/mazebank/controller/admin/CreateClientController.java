package com.fisthu.mazebank.controller.admin;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {
    public TextField firstNameField;
    public TextField lastNameField;
    public TextField passwordField;
    public CheckBox payeeAddressCbox;
    public Label payeeAddressLbl;
    public CheckBox checkingAccountCbox;
    public TextField checkingBalanceField;
    public CheckBox savingAccountCbox;
    public TextField savingBalanceField;
    public Button createBtn;
    public Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
