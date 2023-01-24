package com.fisthu.mazebank.controller.admin;

import com.fisthu.mazebank.model.Client;
import com.fisthu.mazebank.model.Model;
import com.fisthu.mazebank.view.ClientCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {
    public TextField payeeAddressSearchField;
    public Button searchBtn;
    public ListView<Client> resultListView;
    public TextField amountField;
    public Button depositBtn;
    private Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchBtn.setOnAction(event -> onClientSearch());
        depositBtn.setOnAction(event -> onDeposit());
    }

    private void onClientSearch() {
        ObservableList<Client> results = Model.INSTANCE.searchClients(payeeAddressSearchField.getText());
        resultListView.setItems(results);
        resultListView.setCellFactory(param -> new ClientCellFactory());
        client = results.isEmpty() ? null : results.get(0);
    }

    private void onDeposit() {
        if (amountField.getText() != null) {
            double v = Double.parseDouble(amountField.getText());
            double newBalance = v + client.savingAccountProperty().get().balanceProperty().get();
            Model.INSTANCE.getDatabaseDriver().depositSaving(client.payeeAddressProperty().get(), newBalance);

            resetField();
        }
    }

    private void resetField() {
        payeeAddressSearchField.setText("");
        amountField.setText("");
    }
}
