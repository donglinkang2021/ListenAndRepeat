package com.example.myjavafxdemo2.listenerandrepeater;

public class MyRepeaterInfo {
    private static int layTimePerStep = 1500;
    private static int loops = 1;

    private static int ScreenWidth = 1920; // 屏幕尺寸宽度
    private static int ScreenHeight = 1080; // 屏幕尺寸高度

    private static double scaleEps = 1; // 缩放比例
    private static int layTimePerLoop = 1000;

    public static double getScaleEps() {
        return scaleEps;
    }

    public static void setScaleEps(double scaleEps) {
        MyRepeaterInfo.scaleEps = scaleEps;
    }

    public static void setScreenHeight(int screenHeight) {
        ScreenHeight = screenHeight;
    }

    public static void setScreenWidth(int screenWidth) {
        ScreenWidth = screenWidth;
    }

    public static int getScreenWidth() {
        return ScreenWidth;
    }

    public static int getScreenHeight() {
        return ScreenHeight;
    }

    public static void setLoops(int loops) {
        MyRepeaterInfo.loops = loops;
    }

    public static int getLoops() {
        return loops;
    }

    public static void setLayTimePerStep(int layTimePerStep) {
        MyRepeaterInfo.layTimePerStep = layTimePerStep;
    }

    public static int getLayTimePerStep() {
        return layTimePerStep;
    }

    public static void setLayTimePerLoop(int layTimePerLoop) {
        MyRepeaterInfo.layTimePerLoop = layTimePerLoop;
    }

    public static int getLayTimePerLoop() {
        return layTimePerLoop;
    }
}
