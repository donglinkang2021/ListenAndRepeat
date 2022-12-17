package com.example.myjavafxdemo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class listenAndRepeatApp extends Application {

    private Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        showMainWindow();
    }

    public void showMainWindow()  {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(listenAndRepeatApp.class.getResource("demo1.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            mainController controller = fxmlLoader.getController();
            controller.setMain(this);
            scene.getStylesheets().add("demo.css");
            primaryStage.setTitle("👂Listen, and ✋Repeat...");
            primaryStage.setScene(scene);
            // 不显示窗口上方的工具栏
//        primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void openProcessFileByFile() {
        try {
            // 加载视图
            FXMLLoader loader = new FXMLLoader(listenAndRepeatApp.class.getResource("processFileByFile.fxml"));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add("demo.css");
            processFileByFileController controller = loader.getController();
            // 针对controller对象做一些初始化的工作
            // controller.init();

            // 设置舞台
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setTitle("Repeat File by File");
            // 显示
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        launch();
    }
}
