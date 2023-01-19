package com.fisthu.mazebank.controller.client;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountController implements Initializable {
    public Label checkingAccountNo;
    public Label transLimit;
    public Label checkingCreatedDate;
    public Label checkingBalance;
    public Label savingAccountNo;
    public Label savingWithdrawLimit;
    public Label savingCreatedDate;
    public Label savingBalance;
    public TextField amountToSaving;
    public Button transToSavingBtn;
    public TextField amountToChecking;
    public Button transToCheckingBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
