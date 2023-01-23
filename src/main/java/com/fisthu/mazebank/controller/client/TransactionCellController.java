package com.fisthu.mazebank.controller.client;

import com.fisthu.mazebank.model.Transaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {
    public Label transDateLbl;
    public FontAwesomeIconView inIcon;
    public FontAwesomeIconView outIcon;
    public Label senderLbl;
    public Label receiverLbl;
    public Label amountLbl;

    private final Transaction transaction;

    public TransactionCellController(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
