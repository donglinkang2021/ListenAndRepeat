package com.example.myjavafxdemo2;

import com.example.myjavafxdemo2.listenerandrepeater.MyListener;
import com.example.myjavafxdemo2.listenerandrepeater.MyRepeater;
import com.example.myjavafxdemo2.listenerandrepeater.MyRepeaterInfo;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class demo0Controller {
    public TextArea listenerTextArea; // 不能加static，否则原本的打开和保存功能会失效
    public TextField loopTimesTextField;
    public TextField stepDelayTextField;
    public TextField loopDelayTextField;

    public Thread listenerThread; // 创建一个线程的引用，用于监听键盘输入

    // 判断输入的字符串是否为数字
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void onButtonListenerClicked() {
        MyControllerOwnListener.addListener();
        Stage stage = (Stage) listenerTextArea.getScene().getWindow();
        stage.setIconified(true);

        listenerTextArea.setText("监听中...");
        // 动态更新TextArea的内容, 用于显示监听到的内容,并且一直显示最新的内容（下滑到最新的内容）, 按下esc键停止更新

        MyListener.setListenerContent("");
        listenerThread = new Thread(() -> {
            MyListener.startListen();
            while (true) {
                if(!MyListener.getIsListening()){
                    Thread.currentThread().interrupt(); // 标记线程为中断状态
                    break;
                }
                // 用Platform.runLater()方法来更新TextArea的内容 但是需要先结束之前的Platform.runLater()方法 可以用AtomicBoolean来实现
                Platform.runLater(() -> {
                    listenerTextArea.clear();
                    listenerTextArea.setText(MyListener.getListenerContent());
                    listenerTextArea.setScrollTop(Double.MAX_VALUE);
                });
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            MyListener.stopListen();
//            stage.setIconified(false); <--原因是javafx允许多线程执行后台程序，但是不允许多线程操作javafx的UI，如果要操作UI，必须使用Platform.runLater()方法
            Platform.runLater(() -> {
                stage.setIconified(false);
            });
        });
        listenerThread.start();

    }

    public void onButtonListenerClicked2() {
        MyControllerOwnListener.addListener();
        MyListener.startListen();
        while (true) {
            if(!MyListener.getIsListening()){
                break;
            }
            Platform.runLater(() -> {
                listenerTextArea.clear();
                listenerTextArea.setText(MyListener.getListenerContent());
                listenerTextArea.setScrollTop(Double.MAX_VALUE);
            });
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
//            stage.setIconified(false); <--原因是javafx允许多线程执行后台程序，但是不允许多线程操作javafx的UI，如果要操作UI，必须使用Platform.runLater()方法
//            Platform.runLater(() -> {
//                stage.setIconified(false);
//            });
    }
    public void onButtonRepeaterClicked() {
        // 最小化窗口
        MyControllerOwnListener.addListener();
        MyRepeaterInfo.setIsRepeating(true);
        Stage stage = (Stage) listenerTextArea.getScene().getWindow();
        stage.setIconified(true);
        Platform.runLater(()->{
            if (isNumeric(loopTimesTextField.getText()) && isNumeric(stepDelayTextField.getText()) && isNumeric(loopDelayTextField.getText())) {
                MyRepeater.initRobot();
                MyRepeater.initScreenSizeToRobot();
                MyRepeaterInfo.setLoops(Integer.parseInt(loopTimesTextField.getText()));
                MyRepeaterInfo.setLayTimePerStep(Integer.parseInt(stepDelayTextField.getText()));
                MyRepeaterInfo.setLayTimePerLoop(Integer.parseInt(loopDelayTextField.getText()));
                MyRepeater.doAction(()->
                    {
                        MyRepeater.doTaskFromString(listenerTextArea.getText());
                    }
                );
            } else {
                listenerTextArea.setText("输入的数字不合法");
            }
            stage.setIconified(false);
        });
    }

    // 恢复窗口
    public void onButtonRestoreClicked() {
        Stage stage = (Stage) listenerTextArea.getScene().getWindow();
        stage.setIconified(false);
    }

    private void writeFileToTextArea(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            listenerTextArea.clear();
            String line = null;
            while ((line = br.readLine()) != null) {
                listenerTextArea.appendText(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 点击按钮打开文件资源管理器保存txt文件
    public void onButtonSaveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("保存监听文件");
        fileChooser.setInitialFileName("tmp.txt");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                bw.write(listenerTextArea.getText());
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // 点击按钮打开文件资源管理器打开txt文件
    public void onButtonOpenFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("打开监听文件");
        fileChooser.setInitialFileName("tmp.txt");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            writeFileToTextArea(file);
        }
    }


    static class MyControllerOwnListener implements NativeKeyListener {
        public static void addListener() {
            Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);
            try {
                GlobalScreen.registerNativeHook();
            }
            catch (NativeHookException ex) {
                System.err.println("There was a problem registering the native hook.");
                System.err.println(ex.getMessage());
                System.exit(1);
            }
            GlobalScreen.addNativeKeyListener(new MyControllerOwnListener());
        }

        public static void removeListener() {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
            if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
                MyListener.setIsListening(false);
                MyRepeaterInfo.setIsRepeating(false);

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
