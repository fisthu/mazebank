package com.fisthu.mazebank.controller.client;

import com.fisthu.mazebank.model.Client;
import com.fisthu.mazebank.model.Model;
import com.fisthu.mazebank.model.Transaction;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public Text usernameTxt;
    public Label loginDateLbl;
    public Label checkingBalanceLbl;
    public Label checkingAccountNumberLbl;
    public Label savingBalanceLbl;
    public Label savingAccNoLbl;
    public Label incomeLbl;
    public Label expenseLbl;
    public ListView<Transaction> transListView;
    public TextField payeeAddressField;
    public TextField amountField;
    public TextArea messageTxtArea;
    public Button sendMoneyBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bind();
    }

    private void bind() {
        Client client = Model.INSTANCE.getClient();
        usernameTxt.textProperty().bind(Bindings.concat("Hi, ").concat(client.firstnameProperty()));
        loginDateLbl.setText("Today, %s".formatted(LocalDate.now()));
        checkingBalanceLbl.textProperty().bind(client.checkingAccountProperty().get().balanceProperty().asString());
        checkingAccountNumberLbl.textProperty().bind(client.checkingAccountProperty().get().accountNumberProperty());
        savingBalanceLbl.textProperty().bind(client.savingAccountProperty().get().balanceProperty().asString());
        savingAccNoLbl.textProperty().bind(client.savingAccountProperty().get().accountNumberProperty());
    }
}
