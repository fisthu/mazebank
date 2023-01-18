module com.fisthu.mazebank {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.fisthu.mazebank to javafx.fxml;
    exports com.fisthu.mazebank;
    exports com.fisthu.mazebank.controller;
    exports com.fisthu.mazebank.controller.admin;
    exports com.fisthu.mazebank.controller.client;
    exports com.fisthu.mazebank.model;
    exports com.fisthu.mazebank.view;
}