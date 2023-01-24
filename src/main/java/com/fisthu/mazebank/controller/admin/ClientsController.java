package com.fisthu.mazebank.controller.admin;

import com.fisthu.mazebank.model.Client;
import com.fisthu.mazebank.model.Model;
import com.fisthu.mazebank.view.ClientCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {
    public ListView<Client> clientsListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();

        clientsListView.setItems(Model.INSTANCE.getClients());
        clientsListView.setCellFactory(param -> new ClientCellFactory());
    }

    private void init() {
        if (Model.INSTANCE.getClients().isEmpty()) {
            Model.INSTANCE.setClients();
        }
    }
}
