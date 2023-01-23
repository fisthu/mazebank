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

    }
}