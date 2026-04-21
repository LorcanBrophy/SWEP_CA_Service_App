module com.example.setupark {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.setupark.model;
    opens com.example.setupark to javafx.fxml;

    exports com.example.setupark.controller;
    opens com.example.setupark.controller to javafx.fxml;

    exports com.example.setupark.main;
    opens com.example.setupark.main to javafx.fxml;
}