package com.example.myjavafxdemo2.demo.robotdemo;


import java.awt.*;
import java.awt.event.InputEvent;

public class MyMouseAction {

    // 一个全局动态robot
    static Robot robot = null;

    public static void doMouseAction() {
        try {
            Thread.sleep(200); // 最低下限是200ms，如果两个动作之间没有延时会导致不执行语句
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch (MyMouseInfo.getActionType()) {
            case STOP:
                break;
            case MOVE:
                moveMouse();
                break;
            case LEFT_CLICK:
                leftClick();
                break;
            case RIGHT_CLICK:
                rightClick();
                break;
            case MIDDLE_CLICK:
                middleClick();
                break;
            case DRAG:
                dragMouse();
                break;
            case WHEEL:
                wheel();
                break;
        }
    }

    public static void moveMouse() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        assert robot != null;
        robot.mouseMove(MyMouseInfo.getEndX(), MyMouseInfo.getEndY());
    }

    public static void leftClick() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        assert robot != null;
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public static void rightClick() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        assert robot != null;
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }

    public static void middleClick() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        assert robot != null;
        robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
    }


    public static void dragMouse() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        assert robot != null;
        robot.mouseMove(MyMouseInfo.getBeginX(), MyMouseInfo.getBeginY());
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseMove(MyMouseInfo.getEndX(), MyMouseInfo.getEndY());
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }


    public static void wheel() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        assert robot != null;
        robot.mouseWheel(MyMouseInfo.getWheelAmount());
    }

    // region test
    public static void testDrag() {
        MyMouseInfo.setMouseAction(MyMouseInfo.ActionType.DRAG);
        MyMouseInfo.setBeginPosition(562, 317);
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MyMouseInfo.setEndPosition(1453, 718);
            MyMouseAction.doMouseAction();
        });
        thread.start();
        MyMouseAction.doMouseAction();
    }

    public static void testWheel() {
        MyMouseInfo.setMouseAction(MyMouseInfo.ActionType.MOVE);
        MyMouseInfo.setEndPosition(1453, 718);
        MyMouseAction.doMouseAction();
        MyMouseInfo.setMouseAction(MyMouseInfo.ActionType.WHEEL);
        int sign = 1;
        for (int j = 0; j < 10; j++) {
            sign = -sign;
            MyMouseInfo.setWheelAmount(sign * 10);
            for (int i = 0; i < 10; i++) {
                MyMouseAction.doMouseAction();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void testClick() {
        MyMouseInfo.setMouseAction(MyMouseInfo.ActionType.MOVE);
        MyMouseInfo.setEndPosition(1453, 718);
        MyMouseAction.doMouseAction();
        MyMouseInfo.setMouseAction(MyMouseInfo.ActionType.LEFT_CLICK);
        MyMouseAction.doMouseAction();

        MyMouseInfo.setMouseAction(MyMouseInfo.ActionType.RIGHT_CLICK);
        MyMouseAction.doMouseAction();

        MyMouseInfo.setMouseAction(MyMouseInfo.ActionType.MIDDLE_CLICK);
        MyMouseAction.doMouseAction();
    }
    // endregion


    public static void testListenerMessage(){
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        assert robot != null;
//        Mouse Pressed: 2 at 832, 548
        robot.mouseMove(832, 548);
        robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
//        Mouse Released: 2 at 832, 548
        robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
//        Key Pressed: 17 (W)
        robot.keyPress(17);
//        Key Released: 17 (W)
        robot.keyRelease(17);
//        Mouse Pressed: 1 at 1347, 668
        robot.mouseMove(1347, 668);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//        Mouse Released: 1 at 1347, 668
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//        Key Pressed: 46 (C)
        robot.keyPress(46);
//        Key Pressed: 18 (E)
        robot.keyPress(18);
//        Key Released: 46 (C)
        robot.keyRelease(46);
//        Key Released: 18 (E)
        robot.keyRelease(18);
//        Key Pressed: 13 (等号)
        robot.keyPress(13);

    }


    public static void main(String[] args) {
//        testDrag();
//        testWheel();
//        testClick();
        testListenerMessage();
    }

}
