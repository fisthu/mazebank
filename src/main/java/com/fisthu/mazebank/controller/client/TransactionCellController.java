package com.fisthu.mazebank.controller.client;

import com.fisthu.mazebank.model.Model;
import com.fisthu.mazebank.model.Transaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {
  public Label transDateLbl;
  public FontAwesomeIconView inIcon;
  public FontAwesomeIconView outIcon;
  public Label senderLbl;
  public Label receiverLbl;
  public Label amountLbl;
  public Button msgBtn;

  private final Transaction transaction;

  public TransactionCellController(Transaction transaction) {
    this.transaction = transaction;
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    senderLbl.textProperty().bind(transaction.senderProperty());
    receiverLbl.textProperty().bind(transaction.receiverProperty());
    amountLbl.textProperty().bind(transaction.amountProperty().asString());
    transDateLbl.textProperty().bind(transaction.dateProperty().asString());

    transactionIcon();
    msgBtn.setOnAction(event -> onMsgBtn());
  }

  private void onMsgBtn() {
    Model.INSTANCE
        .getViewFactory()
        .showMessageWindow(senderLbl.getText(), transaction.messageProperty().get());
  }

  private void transactionIcon() {
    if (transaction
        .senderProperty()
        .get()
        .equalsIgnoreCase(Model.INSTANCE.getClient().payeeAddressProperty().get())) {
      inIcon.setFill(Color.rgb(240, 240, 240));
      outIcon.setFill(Color.RED);
    } else {
      inIcon.setFill(Color.GREEN);
      outIcon.setFill(Color.rgb(240, 240, 240));
    }
  }
}
