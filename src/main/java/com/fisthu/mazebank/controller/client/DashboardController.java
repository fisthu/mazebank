package com.fisthu.mazebank.controller.client;

import com.fisthu.mazebank.model.Client;
import com.fisthu.mazebank.model.Model;
import com.fisthu.mazebank.model.Transaction;
import com.fisthu.mazebank.view.TransactionCellFactory;
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
        initLatestTransactionList();
        transListView.setItems(Model.INSTANCE.getLatestTransactions());
        transListView.setCellFactory(param -> new TransactionCellFactory());
        sendMoneyBtn.setOnAction(event -> onSendMoney());
    }

    private void bind() {
        loginDateLbl.setText("Today, %s".formatted(LocalDate.now()));

        Client client = Model.INSTANCE.getClient();
        usernameTxt.textProperty().bind(Bindings.concat("Hi, ").concat(client.firstnameProperty()));
        checkingBalanceLbl.textProperty().bind(client.checkingAccountProperty().get().balanceProperty().asString());
        checkingAccountNumberLbl.textProperty().bind(client.checkingAccountProperty().get().accountNumberProperty());
        savingBalanceLbl.textProperty().bind(client.savingAccountProperty().get().balanceProperty().asString());
        savingAccNoLbl.textProperty().bind(client.savingAccountProperty().get().accountNumberProperty());
    }

    private void initLatestTransactionList() {
        if (Model.INSTANCE.getLatestTransactions().isEmpty()) {
            Model.INSTANCE.setLatestTransactions();
        }
    }

    private void onSendMoney() {
        String receiver = payeeAddressField.getText();
        double amount = Double.parseDouble(amountField.getText());
        String text = messageTxtArea.getText();
        String sender = Model.INSTANCE.getClient().payeeAddressProperty().get();

        Model.INSTANCE.sendMoney(receiver, sender, amount);
        Model.INSTANCE.getDatabaseDriver().newTransaction(receiver, sender, amount, text);

        payeeAddressField.setText("");
        amountField.setText("");
        messageTxtArea.setText("");
    }
}
