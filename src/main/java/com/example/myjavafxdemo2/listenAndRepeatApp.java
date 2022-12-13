package com.example.myjavafxdemo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class listenAndRepeatApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(listenAndRepeatApp.class.getResource("demo0.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Listen and Repeat");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
