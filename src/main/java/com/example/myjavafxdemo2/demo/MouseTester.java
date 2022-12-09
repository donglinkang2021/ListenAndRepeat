package com.example.myjavafxdemo2.demo;

public class MouseTester {
    public static void main(String[] args) {
        GetMousePosition.getPosXYAndRecord(); // 获取鼠标运动轨迹
        PlotMousePosition.plot(GetMousePosition.PosXY); // 绘制鼠标运动轨迹
        MouseRobot.copyMouseAction(GetMousePosition.PosXY); // 模拟鼠标运动轨迹
    }
}
