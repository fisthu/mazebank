package com.fisthu.mazebank.controller.client;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
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
    public ListView transListView;
    public TextField payeeAddressField;
    public TextField amountField;
    public TextArea messageTxtArea;
    public Button sendMoneyBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
