package com.example.myjavafxdemo2.listenerandrepeater;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyRepeater implements NativeKeyListener {
    static Robot robot = null;
    private static void initRobot() {
        RepeaterOwnListener.addListener(); // 注册监听器允许中断运行
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    public static void doAction(Runnable action) {
        for (int i = 0; i < MyRepeaterInfo.getLoops(); i++) {
            action.run();
            robot.delay(MyRepeaterInfo.getLayTimePerLoop());
            // 当用户按下Esc键时，退出循环
        }
    }

    public static void test8(){
        String str =    "1 (mousePress) 1 (btn) 717 481 (pos)\n" +
                        "2 (mouseRelease) 1 (btn) 717 481 (pos)\n" +
                        "3 (mouseWheelMove) 1 (deg)\n" +
                        "4 (keyPress) 57 (空格)\n" +
                        "5 (keyRelease) 57 (空格)\n";
        int[] buffer; // 用于存储读取到的字符
        // 将换行符替换为空格 以便于分割
        str = str.replace("\r", " ");
        str = str.replace("\n", " ");
        buffer = java.util.Arrays.stream(str.split(" "))
                .filter(s -> s.matches("\\d+")) // 过滤出数字 \\d+表示一个或多个数字
                .mapToInt(Integer::parseInt)
                .toArray();
        doTaskFromData(buffer);
    }

    public static void test9(){
        String filePath = "./output.txt";
        readFromFileAndProcess(filePath);
    }

    public static void test10(){
//        1 (mousePress) 1 (btn) 1607 613 (pos)
        robot.mouseMove(1607, 613);
//        robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
        robot.delay(100);

//        mousePressBtnAt(
//                ChangeBtnType.mouseSpeakToRobot(1),
//                1607,
//                613
//        );
//        2 (mouseRelease) 1 (btn) 1647 842 (pos)
//        mouseReleaseBtnAt(
//                ChangeBtnType.mouseSpeakToRobot(1),
//                1647,
//                842
//        );
//        1 (mousePress) 1 (btn) 1636 675 (pos)
//        2 (mouseRelease) 1 (btn) 1636 675 (pos)
    }


    public static void readFromFileAndProcess(String filePath) {
        // 打开文件读取文件并打印
        try(
                FileReader fileReader = new FileReader(filePath); // FileReader是字符流，读取的是字符
        ) {
            int ch;
            StringBuilder stringBuilder = new StringBuilder();
            while ((ch = fileReader.read()) != -1) {
                stringBuilder.append((char) ch);
            }
            int[] buffer = getNumsFromString(stringBuilder.toString()
                    .replace("\r", " ")
                    .replace("\n", " ")
            );
            doTaskFromData(buffer);
//            输出测试观察是否准确读入数字
//            for (int i : buffer) {
//                System.out.println(i);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void doTaskFromData(int[] buffer) {
        //         对buffer进行处理
        //         1. 按照actionType进行分组
        //             - 如果第一个数字是1，那么就是一个mousePress, 读三个数字，分别是btnType, x, y
        //                 - 调用MouseButtonType.speakToRobot(btnType)转换为robot可以识别的btnType
        //                 - 调用mousePressBtnAt(btnType, x, y)进行模拟
        //             - 如果第一个数字是2，那么就是一个mouseRelease, 读三个数字，分别是btnType, x, y
        //                 - 调用MouseButtonType.speakToRobot(btnType)转换为robot可以识别的btnType
        //                 - 调用mouseReleaseBtnAt(btnType, x, y)进行模拟
        //             - 如果第一个数字是3，那么就是一个mouseWheelMove, 读一个数字，是deg
        //                 - 调用mouseWheelMove(deg)进行模拟
        //             - 如果第一个数字是4，那么就是一个keyPress, 读一个数字，是keyCode
        //                 - 调用keyBoardPress(keyCode)进行模拟
        //             - 如果第一个数字是5，那么就是一个keyRelease, 读一个数字，是keyCode
        //                 - 调用keyBoardRelease(keyCode)进行模拟
        int i = 0;
        while (i < buffer.length) {
            switch (buffer[i]) {
                case 1 -> {
                    // mousePress
                    mousePressBtnAt(
                            ChangeBtnType.mouseSpeakToRobot(buffer[i + 1]),
                            buffer[i + 2],
                            buffer[i + 3]
                    );
                    i += 4;
                }
                case 2 -> {
                    // mouseRelease
                    mouseReleaseBtnAt(
                            ChangeBtnType.mouseSpeakToRobot(buffer[i + 1]),
                            buffer[i + 2],
                            buffer[i + 3]
                    );
                    i += 4;
                }
                case 3 -> {
                    // mouseWheelMove
                    mouseWheelMove(
                            buffer[i + 1]
                    );
                    i += 2;
                }
                case 4 -> {
                    // keyPress
                    keyBoardPress(
                            ChangeBtnType.keyboardSpeakToRobot(buffer[i + 1])
                    );
                    i += 2;
                }
                case 5 -> {
                    // keyRelease
                    keyBoardRelease(
                            ChangeBtnType.keyboardSpeakToRobot(buffer[i + 1])
                    );
                    i += 2;
                }
                default -> throw new IllegalArgumentException("Invalid action type: " + buffer[i]);
            }
        }
    }

    public static int[] getNumsFromString(String input) {
        // 使用streamAPI将字符串数组转换为整数数组
        return java.util.Arrays.stream(input.split(" "))
                .filter(s -> s.matches("-?\\d+")) // 过滤出数字 \\d+表示一个或多个数字
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    // region keyBoard and Mouse Action
    private static void keyBoardRelease(int vkT) {
        robot.keyRelease(vkT);
        robot.delay(MyRepeaterInfo.getLayTimePerStep());
    }

    private static void keyBoardPress(int vkT) {
        robot.keyPress(vkT);
        robot.delay(MyRepeaterInfo.getLayTimePerStep());
    }

    private static void mouseReleaseBtnAt(int button1DownMask, int x, int y) {
        robot.mouseMove(pixelX(x), pixelY(y));
        robot.delay(MyRepeaterInfo.getLayTimePerStep());
        robot.mouseRelease(button1DownMask);
        robot.delay(MyRepeaterInfo.getLayTimePerStep());
    }

    private static void mousePressBtnAt(int button1DownMask, int x, int y) {
        robot.mouseMove(pixelX(x), pixelY(y));
        robot.delay(MyRepeaterInfo.getLayTimePerStep());
        robot.mousePress(button1DownMask);
        robot.delay(MyRepeaterInfo.getLayTimePerStep());
    }

    private static void mouseWheelMove(int wheelAmt) {
        robot.mouseWheel(wheelAmt);
        robot.delay(MyRepeaterInfo.getLayTimePerStep());
    }
    // endregion


    public static void initScreenSizeToRobot() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        if (gs.length == 1) {
            MyRepeaterInfo.setScreenWidth(Toolkit.getDefaultToolkit().getScreenSize().width);
            MyRepeaterInfo.setScreenHeight(Toolkit.getDefaultToolkit().getScreenSize().height);
            int resolutionWidth = gs[0].getDisplayMode().getWidth();
            MyRepeaterInfo.setScaleEps(
                    (MyRepeaterInfo.getScreenWidth() * 1.0 ) / resolutionWidth
            );
        }
    }
    public static int pixelX(int x) {
        return (int) (x * MyRepeaterInfo.getScaleEps());
    }

    public static int pixelY(int y) {
        return (int) (y * MyRepeaterInfo.getScaleEps());
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        // 按下esc时，退出程序
        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
                System.exit(0);
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
    }

    public static void main(String[] args) {
        initRobot();
        assert robot != null;
        MyRepeaterInfo.setLoops(1);
        MyRepeaterInfo.setLayTimePerStep(200);
        MyRepeaterInfo.setLayTimePerLoop(800);
        initScreenSizeToRobot();
        doAction(MyRepeater::test9);
    }
}


class RepeaterOwnListener implements NativeKeyListener {
    public static void addListener(){
        Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new RepeaterOwnListener());
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        // 按下esc时，退出程序
        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
                System.exit(0);
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
    }
}