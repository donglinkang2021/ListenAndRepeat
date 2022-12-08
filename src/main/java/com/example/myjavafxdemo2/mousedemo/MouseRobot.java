package com.example.myjavafxdemo2.mousedemo;

import java.awt.*;

public class MouseRobot {
    // 移动鼠标到指定位置
    public static void moveMouse(int x, int y) {
        try {
            Robot robot = new Robot();
            robot.mouseMove(x, y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    // 点击鼠标左键
    public static void clickMouse() {
        try {
            Robot robot = new Robot();
            robot.mousePress(16); // 16代表鼠标左键
            robot.mouseRelease(16); // 释放鼠标左键
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws AWTException {
        System.out.println("Hello World!");
        Robot robot = new Robot();
        robot.mousePress(16); // 16代表鼠标左键
        robot.mouseRelease(16); // 释放鼠标左键
    }

    public static void copyMouseAction(int[][] PosXY) {
        try {
            Robot robot = new Robot();
            for (int i = 0; i < PosXY.length; i++) {
                robot.mouseMove(PosXY[i][0], PosXY[i][1]);
                Thread.sleep(10);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
