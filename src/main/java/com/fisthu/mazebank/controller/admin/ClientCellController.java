package com.fisthu.mazebank.controller.admin;

import com.fisthu.mazebank.model.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {
    public Label firstnameLbl;
    public Label lastnameLbl;
    public Label pAddressLbl;
    public Label chAccLbl;
    public Label svAccLbl;
    public Label dateLbl;
    public Button deleteBtn;

    private final Client client;

    public ClientCellController(Client client) {
        this.client = client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstnameLbl.textProperty().bind(client.firstnameProperty());
        lastnameLbl.textProperty().bind(client.lastnameProperty());
        pAddressLbl.textProperty().bind(client.payeeAddressProperty());
        if (client.checkingAccountProperty().getValue() != null) {
            chAccLbl.textProperty().bind(client.checkingAccountProperty().getValue().accountNumberProperty());
        }
        if (client.savingAccountProperty().getValue() != null) {
            svAccLbl.textProperty().bind(client.savingAccountProperty().getValue().accountNumberProperty());
        }
        dateLbl.textProperty().bind(client.dateCreatedProperty().asString());
    }
}
