package com.fisthu.mazebank.view;

import com.fisthu.mazebank.controller.admin.ClientCellController;
import com.fisthu.mazebank.model.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class ClientCellFactory extends ListCell<Client> {

    @Override
    protected void updateItem(Client client, boolean empty) {
        super.updateItem(client, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/admin/ClientCell.fxml"));
            fxmlLoader.setController(new ClientCellController(client));
            setText(null);

            try {
                setGraphic(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
