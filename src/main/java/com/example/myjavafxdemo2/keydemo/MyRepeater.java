package com.example.myjavafxdemo2.keydemo;

import java.awt.*;
import java.awt.event.KeyEvent;


public class MyRepeater {
    // 把一个函数传入下面函数，在下面函数中设定执行次数
    static Robot robot = null;
    private static void initRobot() {
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
    public static void test5(){
//        Mouse Pressed: 1 at 1269, 466
        mousePressBtnAt(1269, 466, KeyEvent.BUTTON1_DOWN_MASK);
//        Mouse Released: 1 at 1269, 466
        mouseReleaseBtnAt(1269, 466, KeyEvent.BUTTON1_DOWN_MASK);
//        Mouse Pressed: 2 at 1269, 466
        mousePressBtnAt(1269, 466, KeyEvent.BUTTON3_DOWN_MASK);
//        Mouse Released: 2 at 1269, 466
        mouseReleaseBtnAt(1269, 466, KeyEvent.BUTTON3_DOWN_MASK);
//        Key Pressed: 17 (W)
        keyBoardPress(KeyEvent.VK_W);
//        Key Released: 17 (W)
        keyBoardRelease(KeyEvent.VK_W);
//        Mouse Pressed: 1 at 1139, 765
        mousePressBtnAt(1139, 765, KeyEvent.BUTTON1_DOWN_MASK);
//        Mouse Released: 1 at 1139, 765
        mouseReleaseBtnAt(1139, 765, KeyEvent.BUTTON1_DOWN_MASK);
//        Key Pressed: 20 (T)
        keyBoardPress(KeyEvent.VK_T);
//        Key Pressed: 50 (M)
        keyBoardPress(KeyEvent.VK_M);
//        Key Pressed: 25 (P)
        keyBoardPress(KeyEvent.VK_P);
//        Key Released: 20 (T)
        keyBoardRelease(KeyEvent.VK_T);
//        Key Released: 50 (M)
        keyBoardRelease(KeyEvent.VK_M);
//        Key Released: 25 (P)
        keyBoardRelease(KeyEvent.VK_P);
//        Key Pressed: 28 (Enter)
        keyBoardPress(KeyEvent.VK_ENTER);
//        Key Released: 28 (Enter)
        keyBoardRelease(KeyEvent.VK_ENTER);
//        Key Pressed: 17 (W)
        keyBoardPress(KeyEvent.VK_Y);
        keyBoardRelease(KeyEvent.VK_Y);
//        Key Pressed: 1 (Esc)
    }

    public static void test6(){
//        Mouse Pressed: 2 at 1004, 460
        mousePressBtnAt(1004, 460, KeyEvent.BUTTON3_DOWN_MASK);
//        Mouse Released: 2 at 1004, 460
        mouseReleaseBtnAt(1004, 460, KeyEvent.BUTTON3_DOWN_MASK);
//        Mouse Pressed: 1 at 1126, 850
        mousePressBtnAt(1126, 850, KeyEvent.BUTTON1_DOWN_MASK);
//        Mouse Released: 1 at 1126, 850
        mouseReleaseBtnAt(1126, 850, KeyEvent.BUTTON1_DOWN_MASK);
//        Mouse Pressed: 1 at 1514, 753
        mousePressBtnAt(1514, 753, KeyEvent.BUTTON1_DOWN_MASK);
//        Mouse Released: 1 at 1514, 753
        mouseReleaseBtnAt(1514, 753, KeyEvent.BUTTON1_DOWN_MASK);
//        Key Pressed: 28 (Enter)
        keyBoardPress(KeyEvent.VK_ENTER);
//        Key Released: 28 (Enter)
        keyBoardRelease(KeyEvent.VK_ENTER);
//        Mouse Pressed: 2 at 1004, 460
        mousePressBtnAt(1004, 460, KeyEvent.BUTTON1_DOWN_MASK);
//        Mouse Released: 2 at 1004, 460
        mouseReleaseBtnAt(750, 460, KeyEvent.BUTTON1_DOWN_MASK);
//        Key Pressed: 1 (Esc)
    }

    public static void test7(){
//        MousePressed: 2 at 1108, 433
        mousePressBtnAt(1108, 433, KeyEvent.BUTTON3_DOWN_MASK);
//        MouseReleased: 2 at 1108, 433
        mouseReleaseBtnAt(1108, 433, KeyEvent.BUTTON3_DOWN_MASK);
//        MousePressed: 1 at 1197, 798
        mousePressBtnAt(1197, 798, KeyEvent.BUTTON1_DOWN_MASK);
//        MouseReleased: 1 at 1197, 798
        mouseReleaseBtnAt(1197, 798, KeyEvent.BUTTON1_DOWN_MASK);
//        MousePressed: 1 at 1632, 708
        mousePressBtnAt(1632, 708, KeyEvent.BUTTON1_DOWN_MASK);
//        MouseReleased: 1 at 1632, 708
        mouseReleaseBtnAt(1632, 708, KeyEvent.BUTTON1_DOWN_MASK);
//        KeyPressed: 28 (Enter)
        keyBoardPress(KeyEvent.VK_ENTER);
//        KeyReleased: 28 (Enter)
        keyBoardRelease(KeyEvent.VK_ENTER);
//        MousePressed: 1 at 1098, 458
        mousePressBtnAt(1098, 458, KeyEvent.BUTTON1_DOWN_MASK);
//        MouseReleased: 1 at 720, 465
        mouseReleaseBtnAt(720, 465, KeyEvent.BUTTON1_DOWN_MASK);
//        KeyPressed: 1 (Esc)
    }

    private static void keyBoardRelease(int vkT) {
        robot.keyRelease(vkT);
        robot.delay(MyRepeaterInfo.getLayTimePerStep());
    }

    private static void keyBoardPress(int vkT) {
        robot.keyPress(vkT);
        robot.delay(MyRepeaterInfo.getLayTimePerStep());
    }

    private static void mouseReleaseBtnAt(int x, int y, int button1DownMask) {
        robot.mouseMove(x, y);
        robot.delay(MyRepeaterInfo.getLayTimePerStep());
        robot.mouseRelease(button1DownMask);
        robot.delay(MyRepeaterInfo.getLayTimePerStep());
    }

    private static void mousePressBtnAt(int x, int y, int button1DownMask) {
        robot.mouseMove(x, y);
        robot.delay(MyRepeaterInfo.getLayTimePerStep());
        robot.mousePress(button1DownMask);
        robot.delay(MyRepeaterInfo.getLayTimePerStep());
    }

    private static void mouseWheelMove(int wheelAmt) {
        robot.mouseWheel(wheelAmt);
        robot.delay(MyRepeaterInfo.getLayTimePerStep());
    }


    public static void main(String[] args) {
        initRobot();
        assert robot != null;
        MyRepeaterInfo.setLoops(1);
        MyRepeaterInfo.setLayTimePerStep(1000);
        MyRepeaterInfo.setLayTimePerLoop(0);
        doAction(MyRepeater::test6);
    }
}
