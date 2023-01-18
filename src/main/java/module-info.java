module com.fisthu.mazebank {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.fisthu.mazebank to javafx.fxml;
    exports com.fisthu.mazebank;
}