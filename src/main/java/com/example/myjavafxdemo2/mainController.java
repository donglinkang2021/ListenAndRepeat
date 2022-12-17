package com.example.myjavafxdemo2;

import com.example.myjavafxdemo2.listenerandrepeater.MyListener;
import com.example.myjavafxdemo2.listenerandrepeater.MyRepeater;
import com.example.myjavafxdemo2.listenerandrepeater.MyRepeaterInfo;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mainController implements Initializable {
    public TextArea listenerTextArea; // 不能加static，否则原本的打开和保存功能会失效
    public TextField loopTimesTextField;
    public TextField stepDelayTextField;
    public TextField loopDelayTextField;
    public Button listenBtn;
    public Button repeatBtn;
    public MenuBar menuBar;
    public RadioMenuItem isShowInfoRadio;

    public void onButtonRadioMenuItemClicked() {
        if (isShowInfoRadio.isSelected()) {
            MyRepeaterInfo.setIsShowInfo(true);
        } else {
            MyRepeaterInfo.setIsShowInfo(false);
        }
    }

    // 冻结页面
    public void freezePage() {
        listenBtn.setDisable(true);
        repeatBtn.setDisable(true);
        menuBar.setDisable(true);
        loopTimesTextField.setDisable(true);
        stepDelayTextField.setDisable(true);
        loopDelayTextField.setDisable(true);
    }

    // 解冻页面
    public void unfreezePage() {
        listenBtn.setDisable(false);
        repeatBtn.setDisable(false);
        menuBar.setDisable(false);
        loopTimesTextField.setDisable(false);
        stepDelayTextField.setDisable(false);
        loopDelayTextField.setDisable(false);
    }

    // 显示提示信息功能：在按键监听器中调用
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

    // 判断输入的字符串是否为数字
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // listenBtn的点击事件: 创建一个线程开始监听
    public void onButtonListenerClicked() {
        if(MyListener.getIsListening()){ // 双重保险，防止重复监听
            return;
        }
        addListener();
        Stage stage = (Stage) listenerTextArea.getScene().getWindow();
        stage.setIconified(true);
        // 以下代码是为了防止重复点击监听按钮而导致的多个监听器同时运行 冻结按钮
        freezePage();
        listenerTextArea.setDisable(true);
        // 动态更新TextArea的内容, 用于显示监听到的内容,并且一直显示最新的内容（下滑到最新的内容）, 按下esc键停止更新
        MyListener.setListenerContent("");
        new Thread(() -> {
            MyListener.startListen();
            if (MyRepeaterInfo.getIsShowInfo()) {
                Platform.runLater(() -> {
                    showTip("开始监听，按esc键停止监听");
                });
            }
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
            removeListener();
//            stage.setIconified(false); <--原因是javafx允许多线程执行后台程序，但是不允许多线程操作javafx的UI，如果要操作UI，必须使用Platform.runLater()方法
            Platform.runLater(() -> {
                unfreezePage();
                if (MyRepeaterInfo.getIsShowInfo()) {
                    showTip("监听结束");
                }
                listenerTextArea.setDisable(false);
                stage.setIconified(false);
            });
        }).start();
    }

    // repeatBtn的点击事件: 创建一个线程重复监听到的内容
    public void onButtonRepeaterClicked() {
        addListener();
        Stage stage = (Stage) listenerTextArea.getScene().getWindow();
        stage.setIconified(true);
        // 以下代码是为了防止重复点击监听按钮而导致的多个监听器同时运行 冻结按钮
        freezePage();
        new Thread(()->{
            if (isNumeric(loopTimesTextField.getText()) && isNumeric(stepDelayTextField.getText()) && isNumeric(loopDelayTextField.getText())) {
                MyRepeaterInfo.setIsRepeating(true);
                MyRepeater.initRobot();
                MyRepeater.initScreenSizeToRobot();
                MyRepeaterInfo.setLoops(Integer.parseInt(loopTimesTextField.getText()));
                MyRepeaterInfo.setLayTimePerStep(Integer.parseInt(stepDelayTextField.getText()));
                MyRepeaterInfo.setLayTimePerLoop(Integer.parseInt(loopDelayTextField.getText()));
                if (MyRepeaterInfo.getIsShowInfo()) {
                    Platform.runLater(() -> {
                        showTip("开始repeat，按esc键退出执行\n" +
                                "循环次数：" + MyRepeaterInfo.getLoops() + "\n" +
                                "每次循环间隔：" + MyRepeaterInfo.getLayTimePerLoop() + "ms\n" +
                                "每次执行间隔：" + MyRepeaterInfo.getLayTimePerStep() + "ms");
                    });
                }
                MyRepeater.doAction(()->
                    {
                        MyRepeater.doTaskFromString(listenerTextArea.getText()); // <--这里是重复执行的内容 这一句也导致了不能listenerTextArea.setDisable(true);
                    }
                );
                MyRepeaterInfo.setIsRepeating(false);
            } else {
                Platform.runLater(() -> {
                    showTip("输入的数字不合法");
                });
            }
            removeListener();
            Platform.runLater(() -> {
                unfreezePage();
                if (MyRepeaterInfo.getIsShowInfo()) {
                    showTip("repeat结束");
                }
                stage.setIconified(false);
            });
        }).start();

    }


    // help下的点击事件: 显示帮助信息
    public void onButtonAboutThisProjectClicked() {
        // 经历了一系列的尝试, 最后发现只有这种方法可以在javafx中显示html，而且还可以显示图片（webview打包成jar包之后不行）
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/donglinkang2021/ListenAndRepeat"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void onButtonHowToUseClicked() {
        // 打开jar包所在同一级目录下的\html\help.html
        String path = System.getProperty("user.dir") + "\\html\\help.html"; // <--这里是打开的文件路径
        try {
            Desktop.getDesktop().browse(new File(path).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 在textArea中输入所打开的文件内容
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
        if (MyRepeaterInfo.getIsShowInfo()) {
            Platform.runLater(() -> {
                showTip("保存成功");
            });
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
        if (MyRepeaterInfo.getIsShowInfo()) {
            Platform.runLater(() -> {
                showTip("成功打开文件"+file.getName());
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isShowInfoRadio.setSelected(false);
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

    private listenAndRepeatApp listenAndRepeatApp;

    public void setMain(listenAndRepeatApp main) {
        this.listenAndRepeatApp = main;
    }

    public void onButtonOpenRunFileByFile(){
        listenAndRepeatApp.openProcessFileByFile();
    }

    class MyControllerOwnListener implements NativeKeyListener {
        @Override
        public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
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
