package com.example.myjavafxdemo2;

import com.example.myjavafxdemo2.filebyfile.AllFileInfo;
import com.example.myjavafxdemo2.filebyfile.FileRecord;
import com.example.myjavafxdemo2.listenerandrepeater.MyListener;
import com.example.myjavafxdemo2.listenerandrepeater.MyRepeater;
import com.example.myjavafxdemo2.listenerandrepeater.MyRepeaterInfo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class processFileByFileController {

    public AnchorPane myScrollFilePane;
    @FXML
    public Button addBtn;

    @FXML
    public Button delBtn;

    @FXML
    public Button runBtn;

    FileRecord currentFileRecord;


    public void onButtonAddMyFilePane() {
        currentFileRecord = new FileRecord();
        getFileNameAndPath();

        // 获得myFilePaneTemplate.fxml的内容
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myFilePaneTemplate.fxml"));


        // 以这个内容为模板，创建一个新的AnchorPane
        AnchorPane myFilePane = null;
        try {
            myFilePane = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert myFilePane != null;


        for (int i = 0; i < myFilePane.getChildren().size(); i++) {
            HBox hbox = (HBox) myFilePane.getChildren().get(i); // 这个是文件名的TextField
            TextField textField = (TextField) hbox.getChildren().get(1);
            switch (i) {
                case 0 -> textField.textProperty().bindBidirectional(currentFileRecord.getFileNameProperty());
                case 1 -> textField.textProperty().bindBidirectional(currentFileRecord.getLoopTimesProperty());
                case 2 -> textField.textProperty().bindBidirectional(currentFileRecord.getStepDelayProperty());
                case 3 -> textField.textProperty().bindBidirectional(currentFileRecord.getLoopDelayProperty());
                default -> {
                }
            }
            if(i==0){
                textField.setDisable(true); // 文件名不可编辑
            }
        }

//        testIsRecorded(currentFileRecord);
        AllFileInfo.pushFile(currentFileRecord); // 将文件信息添加到AllFileInfo中

        // 把这个新的AnchorPane添加到myScrollFilePane中
        myScrollFilePane.getChildren().add(myFilePane);
        // 调整位置
        final int padding = 12;
        final int firstPaneY = 10;
        myFilePane.setLayoutX(7);
        myFilePane.setLayoutY(
                (myScrollFilePane.getChildren().size()-1) * (myFilePane.getPrefHeight() + padding) + firstPaneY
        );
    }

    private void testIsRecorded(FileRecord fileRecord) {
        System.out.println("fileRecord.getFileNameProperty() = " + fileRecord.getFileNameProperty());
        System.out.println("fileRecord.getLoopTimesProperty() = " + fileRecord.getLoopTimesProperty());
        System.out.println("fileRecord.getStepDelayProperty() = " + fileRecord.getStepDelayProperty());
        System.out.println("fileRecord.getLoopDelayProperty() = " + fileRecord.getLoopDelayProperty());
        System.out.println("fileRecord = " + fileRecord.getFilePath());
    }

    private void getFileNameAndPath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("打开监听文件");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            currentFileRecord.setFileNameProperty(file.getName());
            currentFileRecord.setFilePath(file.getAbsolutePath());
        }
    }

    public void onButtonDelMyFilePane() {
        // 删除myScrollFilePane中的最后一个AnchorPane
        myScrollFilePane.getChildren().remove(myScrollFilePane.getChildren().size()-1);
        AllFileInfo.popFile();
    }
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void onButtonRunMyFilePane() {
        addListener();
        Stage stage = (Stage) myScrollFilePane.getScene().getWindow();
        stage.setIconified(true);
        new Thread(()->{
            MyRepeaterInfo.setIsRepeating(true);
            MyRepeater.initRobot();
            MyRepeater.initScreenSizeToRobot();
            for (int i = 0; i < AllFileInfo.getFileCount(); i++) {
    //            System.out.println("AllFileInfo.getFile("+ i +") = " + AllFileInfo.getFile(i).toString()); // 打印文件信息
                currentFileRecord = AllFileInfo.getFile(i);
                if (isNumeric(currentFileRecord.getLoopTimesProperty().get()) &&
                    isNumeric(currentFileRecord.getStepDelayProperty().get()) &&
                    isNumeric(currentFileRecord.getLoopDelayProperty().get()))
                {
                    MyRepeaterInfo.setLoops(currentFileRecord.getLoopTimes());
                    MyRepeaterInfo.setLayTimePerStep(currentFileRecord.getStepDelay());
                    MyRepeaterInfo.setLayTimePerLoop(currentFileRecord.getLoopDelay());
                    if (MyRepeaterInfo.getIsShowInfo()) {
                        Platform.runLater(() -> {
                            showTip("开始执行文件：" + currentFileRecord.getFileNameProperty().get()+ "\n" +
                                    "循环次数：" + MyRepeaterInfo.getLoops() + "\n" +
                                    "每次循环间隔：" + MyRepeaterInfo.getLayTimePerLoop() + "ms\n" +
                                    "每次执行间隔：" + MyRepeaterInfo.getLayTimePerStep() + "ms");
                        });
                    }
                    MyRepeater.doAction(()->
                            {
                                MyRepeater.readFromFileAndProcess(currentFileRecord.getFilePath());
                            }
                    );
                    if (MyRepeaterInfo.getIsShowInfo()) {
                        Platform.runLater(() -> {
                            showTip("文件：" + currentFileRecord.getFileNameProperty().get() + "执行完毕");
                        });
                    }
                }else {
                    if (MyRepeaterInfo.getIsShowInfo()) {
                        Platform.runLater(() -> {
                            showTip("循环次数、每次循环间隔、每次执行间隔必须为数字");
                        });
                    }
                }
            }
            MyRepeaterInfo.setIsRepeating(false);
            MyRepeaterInfo.setSpacePressed(false);
            removeListener(); // 移除监听
            Platform.runLater(() -> {
                if (MyRepeaterInfo.getIsShowInfo()) {
                    showTip("执行完毕");
                }
                stage.setIconified(false);
            });
        }).start();
    }

    public void showTip(String message) {
        Text text = new Text(message);
        text.setFont(new Font(20));
        text.setFill(Color.GREEN);
        VBox box = new VBox();
        box.getChildren().add(text);
        box.setStyle("-fx-background:transparent;");

        final int width = 400;
        final int height = 100;
        final Scene scene = new Scene(box, width, height);
        scene.setFill(null); // 设置为透明背景

        final Stage stage = new Stage(); // 创建一个新的窗口
        stage.initStyle(StageStyle.TRANSPARENT); // 设置窗口为透明
        stage.setScene(scene); // 设置场景
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds(); // 获取屏幕的边界
        stage.setX(primaryScreenBounds.getWidth() - width); // 设置窗口的x坐标
//        stage.setY(primaryScreenBounds.getHeight() - height); // 设置窗口的y坐标
        stage.setY(20); // 设置窗口的y坐标
        stage.show(); // 显示窗口

        new Thread(() -> {
            try {
                Thread.sleep(1800);
                Platform.runLater(stage::close); // 关闭窗口
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static MyControllerOwnListener myControllerOwnListener;

    public void addListener() {
        Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        myControllerOwnListener = new MyControllerOwnListener();
        GlobalScreen.addNativeKeyListener(myControllerOwnListener);
    }

    public static void removeListener() {
        try {
            GlobalScreen.removeNativeKeyListener(myControllerOwnListener);
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }

    class MyControllerOwnListener implements NativeKeyListener {
        @Override
        public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
            if (!MyRepeaterInfo.getIsKeyCtrl()) {
                return;
            }
            switch (nativeKeyEvent.getKeyCode()){
                case NativeKeyEvent.VC_SPACE -> { // 空格 暂停和继续运行
                    MyRepeaterInfo.setSpacePressed(!MyRepeaterInfo.isSpacePressed());
                    if (MyRepeaterInfo.getIsShowInfo()) {
                        Platform.runLater(() -> {
                            showTip("空格键按下，暂停/继续运行");
                        });
                    }
                }
                case NativeKeyEvent.VC_ESCAPE -> { // 按下esc时，退出程序
                    MyListener.setIsListening(false);
                    MyRepeaterInfo.setIsRepeating(false);
                    removeListener();
                }
                case NativeKeyEvent.VC_LEFT -> { // 按下左键时，增加每步延迟，最大是60000ms，减慢速度
                    if (MyRepeaterInfo.getUserLayTimePerStep() < 60000 - 50) {
                        MyRepeaterInfo.setUserLayTimePerStep(MyRepeaterInfo.getUserLayTimePerStep() + 50);
                    }else {
                        MyRepeaterInfo.setUserLayTimePerStep(60000);
                    }
                    if (MyRepeaterInfo.getIsShowInfo()) {
                        Platform.runLater(() -> {
                            showTip("左键按下，每步延迟增加50ms\n当前延迟为" + MyRepeaterInfo.getUserLayTimePerStep() + "ms");
                        });
                    }
                }
                case NativeKeyEvent.VC_RIGHT -> { // 按下右键时，减少每步延迟，最小为20ms，加快速度
                    if (MyRepeaterInfo.getLayTimePerStep() > 30) {
                        MyRepeaterInfo.setLayTimePerStep(MyRepeaterInfo.getLayTimePerStep() - 10);
                    }else {
                        MyRepeaterInfo.setLayTimePerStep(20);
                    }
                    if (MyRepeaterInfo.getIsShowInfo()) {
                        Platform.runLater(() -> {
                            showTip("右键按下，每步延迟减少10ms\n当前延迟为" + MyRepeaterInfo.getLayTimePerStep() + "ms");
                        });
                    }
                }
                case NativeKeyEvent.VC_UP -> { // 按下上键时，增加每次循环延迟，最大是60000ms，减慢速度
                    if (MyRepeaterInfo.getLayTimePerLoop() < 60000 - 50) {
                        MyRepeaterInfo.setLayTimePerLoop(MyRepeaterInfo.getLayTimePerLoop() + 50);
                    }else {
                        MyRepeaterInfo.setLayTimePerLoop(60000);
                    }
                    if (MyRepeaterInfo.getIsShowInfo()) {
                        Platform.runLater(() -> {
                            showTip("上键按下，每次循环延迟增加50ms\n当前延迟为" + MyRepeaterInfo.getLayTimePerLoop() + "ms");
                        });
                    }
                }
                case NativeKeyEvent.VC_DOWN -> { // 按下下键时，减少每次循环延迟，最小为20ms，加快速度
                    if (MyRepeaterInfo.getLayTimePerLoop() > 30) {
                        MyRepeaterInfo.setLayTimePerLoop(MyRepeaterInfo.getLayTimePerLoop() - 10);
                    }else {
                        MyRepeaterInfo.setLayTimePerLoop(20);
                    }
                    if (MyRepeaterInfo.getIsShowInfo()) {
                        Platform.runLater(() -> {
                            showTip("下键按下，每次循环延迟减少10ms\n当前延迟为" + MyRepeaterInfo.getLayTimePerLoop() + "ms");
                        });
                    }
                }
            }
        }

        @Override
        public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
//            System.out.println("keyReleased");
        }

        @Override
        public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
//            System.out.println("keyTyped");
        }
    }
}
