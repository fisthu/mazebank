package com.fisthu.mazebank.controller.admin;

import com.fisthu.mazebank.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Random;
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
    private String payeeAddress;
    private boolean createCheckingAccount;
    private boolean createSavingAccount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createBtn.setOnAction(event -> createClient());
        payeeAddressCbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                payeeAddress = generatePayeeAddress();
                onCreatePayeeAddress();
            } else {
                payeeAddress = null;
                payeeAddressLbl.setText("");
            }
        });

        checkingAccountCbox.selectedProperty().addListener((observable, oldValue, newValue) -> createCheckingAccount = newValue);
        savingAccountCbox.selectedProperty().addListener((observable, oldValue, newValue) -> createSavingAccount = newValue);

    }

    private void createClient() {
        createAccount();

        String fName = firstNameField.getText();
        String lName = lastNameField.getText();
        String password = passwordField.getText();
        Model.INSTANCE.getDatabaseDriver().createClient(fName, lName, payeeAddress, password);
        errorLabel.setStyle("-fx-text-fill: blue; -fx-font-size: 1.3em; -fx-font-weight: bold");
        errorLabel.setText("Client Created Successfully!");

        emptyField();
    }

    private void createAccount() {
        // generate account number
        var firstSection = "3201";
        var lastSection = Integer.toString(new Random().nextInt(9999) + 1000);
        var accountNumber = firstSection + " " + lastSection;

        if (createCheckingAccount) {
            var balance = Double.parseDouble(checkingBalanceField.getText());
            Model.INSTANCE.getDatabaseDriver().createCheckingAccount(payeeAddress, accountNumber, 10, balance);
        }

        if (createSavingAccount) {
            var savingBalance = Double.parseDouble(savingBalanceField.getText());
            Model.INSTANCE.getDatabaseDriver().createSavingAccount(payeeAddress, accountNumber, 2000, savingBalance);
        }
    }

    private void emptyField() {
        firstNameField.setText("");
        lastNameField.setText("");
        passwordField.setText("");
        payeeAddressCbox.setSelected(false);
        payeeAddressLbl.setText("");
        checkingAccountCbox.setSelected(false);
        checkingBalanceField.setText("");
        savingAccountCbox.setSelected(false);
        savingBalanceField.setText("");
    }

    private void onCreatePayeeAddress() {
        if (firstNameField.getText() != null && lastNameField.getText() != null) {
            payeeAddressLbl.setText(payeeAddress);
        }
    }

    private String generatePayeeAddress() {
        var lastId = Model.INSTANCE.getDatabaseDriver().getLastClientId();
        char fChar = Character.toLowerCase(firstNameField.getText().charAt(0));

        return "@%s%s%d".formatted(fChar, lastNameField.getText(), lastId + 1);
    }
}
