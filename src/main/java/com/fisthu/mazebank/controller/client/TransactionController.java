package com.fisthu.mazebank.controller.client;

import com.fisthu.mazebank.model.Model;
import com.fisthu.mazebank.model.Transaction;
import com.fisthu.mazebank.view.TransactionCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {
    public ListView<Transaction> transactionListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initAllTransactions();
        transactionListView.setItems(Model.INSTANCE.getAllTransactions());
        transactionListView.setCellFactory(param -> new TransactionCellFactory());
    }

    private void initAllTransactions() {
        if (Model.INSTANCE.getAllTransactions().isEmpty()) {
            Model.INSTANCE.setAllTransactions();
        }
    }
}
