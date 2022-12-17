package com.example.myjavafxdemo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class processFileAPP extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(processFileAPP.class.getResource("processFileByFile.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add("demo.css");
        stage.setTitle("Process File");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
