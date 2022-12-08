package com.example.myjavafxdemo2.mousedemo.somedemo;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class JavaFxMouseEventdemo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ListView<String> listView = new ListView<>();
        BorderPane root = new BorderPane();
        root.setCenter(listView);
        //鼠标点击事件
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                onTestMouse(mouseEvent);
            }
        });
        //鼠标进入事件
        listView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("-------------鼠标进入组件-------------");
            }
        });
        //鼠标退出事件
        listView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("-------------鼠标离开组件-------------");
            }
        });
        primaryStage.setTitle("Mouse");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
    //鼠标左键点击情况
    public void onTestMouse(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2){
            System.out.println("双击鼠标左键");
        }else if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1){
            System.out.println("单击鼠标左键");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


