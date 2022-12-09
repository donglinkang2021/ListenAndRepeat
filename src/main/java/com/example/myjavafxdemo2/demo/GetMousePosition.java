package com.example.myjavafxdemo2.demo;

import java.awt.*;

/*
该类用于获取鼠标位置 并绘制出其轨迹图
 */

public class GetMousePosition{//JFrame对应窗口
    // 动态获取窗体外部的鼠标位置
    public static int stepCount = 0; // 采样步数
    public static int stepDistance = 20; // 采样步长
    public static int[][] PosXY = new int[1000][2]; // 采样点坐标
    public static boolean isRecord(int x, int y) {
        // 判断是否记录
        if (stepCount == 0) {
            return true;
        }
        else {
            int lastX = PosXY[stepCount-1][0];
            int lastY = PosXY[stepCount-1][1];
            return Math.abs(x - lastX) > stepDistance || Math.abs(y - lastY) > stepDistance;
        }
    }

    public static void getPosXYAndRecord() {
        do {
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            int x = (int) b.getX();
            int y = (int) b.getY();
            if (isRecord(x, y)) {
                System.out.println("X: " + x + " Y: " + y);
                PosXY[stepCount][0] = x;
                PosXY[stepCount][1] = y;
                stepCount++;
            }
        } while (stepCount != PosXY.length);
    }
}
