package com.fisthu.mazebank.view;

import com.fisthu.mazebank.controller.admin.AdminController;
import com.fisthu.mazebank.controller.client.ClientController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

  private AccountType loginAccountType;
  // Client View
  private AnchorPane dashboardView;
  private AnchorPane transactionView;
  private final ObjectProperty<ClientMenuOption> clientSelectedMenuItem;
  private AnchorPane clientAccountView;

  // admin
  private final ObjectProperty<AdminMenuOption> adminSelectedMenuItem;
  private AnchorPane createClientView;
  private AnchorPane adminClientsView;
  private AnchorPane depositView;

  public ViewFactory() {
    this.loginAccountType = AccountType.CLIENT;
    this.clientSelectedMenuItem = new SimpleObjectProperty<>();
    this.adminSelectedMenuItem = new SimpleObjectProperty<>();
  }

  public AccountType getLoginAccountType() {
    return loginAccountType;
  }

  public void setLoginAccountType(AccountType loginAccountType) {
    this.loginAccountType = loginAccountType;
  }

  public ObjectProperty<ClientMenuOption> getClientSelectedMenuItemProperty() {
    return clientSelectedMenuItem;
  }

  public AnchorPane getDashboardView() throws IOException {
    if (dashboardView == null) {
      dashboardView = new FXMLLoader(getClass().getResource("/fxml/client/Dashboard.fxml")).load();
    }

    return dashboardView;
  }

  public AnchorPane getTransactionView() throws IOException {
    if (transactionView == null) {
      transactionView =
          new FXMLLoader(getClass().getResource("/fxml/client/transaction.fxml")).load();
    }

    return transactionView;
  }

  public AnchorPane getClientAccountView() throws IOException {
    if (clientAccountView == null) {
      clientAccountView =
          new FXMLLoader(getClass().getResource("/fxml/client/Account.fxml")).load();
    }

    return clientAccountView;
  }

  public ObjectProperty<AdminMenuOption> getAdminSelectedMenuItem() {
    return adminSelectedMenuItem;
  }

  public AnchorPane getCreateClientView() throws IOException {
    if (createClientView == null) {
      createClientView =
          new FXMLLoader(getClass().getResource("/fxml/admin/CreateClient.fxml")).load();
    }

    return createClientView;
  }

  public AnchorPane getAdminClientsView() throws IOException {
    if (adminClientsView == null) {
      adminClientsView = new FXMLLoader(getClass().getResource("/fxml/admin/Clients.fxml")).load();
    }

    return adminClientsView;
  }

  public AnchorPane getDepositView() throws IOException {
    if (depositView == null) {
      depositView = new FXMLLoader(getClass().getResource("/fxml/admin/Deposit.fxml")).load();
    }

    return depositView;
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

  public void showAdminWindow() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/admin/Admin.fxml"));
    AdminController adminController = new AdminController();
    fxmlLoader.setController(adminController);

    buildStage(fxmlLoader);
  }

  public void closeStage(Stage stage) {
    stage.close();
  }

  private void buildStage(FXMLLoader fxmlLoader) throws IOException {
    Scene scene = new Scene(fxmlLoader.load());

    Stage stage = new Stage();
    stage.setScene(scene);
    stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/images/icon.png"))));
    stage.setResizable(false);
    stage.setTitle("Maze Bank");
    stage.show();
  }

  public void showMessageWindow(String sender, String message) {
    VBox box = new VBox(5);
    box.setAlignment(Pos.CENTER);
    Label senderLbl = new Label(sender);
    Label messageLbl = new Label(message);

    box.getChildren().addAll(senderLbl, messageLbl);
    StackPane pane = new StackPane();
    pane.getChildren().add(box);
    Scene scene = new Scene(pane, 300, 100);

    Stage stage = new Stage();
    stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/images/icon.png"))));
    stage.setResizable(false);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setTitle("Message");
    stage.setScene(scene);
    stage.show();
  }
}
