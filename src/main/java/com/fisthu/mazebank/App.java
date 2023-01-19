package com.fisthu.mazebank;

import com.fisthu.mazebank.model.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Model.INSTANCE.getViewFactory().showLoginWindow();
    }
}
