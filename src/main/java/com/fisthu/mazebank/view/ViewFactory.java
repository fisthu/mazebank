package com.fisthu.mazebank.view;

import com.fisthu.mazebank.controller.client.ClientController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    private AnchorPane dashboardView;

    public ViewFactory() {
    }

    public AnchorPane getDashboardView() throws IOException {
        if (dashboardView == null) {
            dashboardView = new FXMLLoader(getClass().getResource("/fxml/client/Dashboard.fxml")).load();
        }

        return dashboardView;
    }

    public void showLoginWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        buildStage(fxmlLoader);
    }

    public void showClientWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/client/Client.fxml"));
        fxmlLoader.setController(new ClientController());

        buildStage(fxmlLoader);
    }

    private void buildStage(FXMLLoader fxmlLoader) throws IOException {
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Maze Bank");
        stage.show();
    }
}
