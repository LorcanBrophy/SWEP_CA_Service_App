package com.example.setupark.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // load the fxml
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/setupark/view.fxml"));

        // put the loaded fxml into a scene
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);

        // window setup
        stage.setScene(scene);

        stage.show();
    }
}
