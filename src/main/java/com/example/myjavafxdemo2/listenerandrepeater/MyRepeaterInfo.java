package com.example.myjavafxdemo2.listenerandrepeater;

public class MyRepeaterInfo {
    private static boolean isRepeating = false; // 是否正在重复
    private static int layTimePerStep = 1500; // 每步延时 ms (默认1500ms) 每步便于修改
    private static int UserLayTimePerStep = 1500; // 用于保存用户设置的值，用于恢复
    private static int loops = 1;

    private static int ScreenWidth = 1920; // 屏幕尺寸宽度
    private static int ScreenHeight = 1080; // 屏幕尺寸高度

    private static double scaleEps = 1; // 缩放比例
    private static int layTimePerLoop = 1000;

    private static boolean SpacePressed = false;

    public static void setIsRepeating(boolean isRepeating) {
        MyRepeaterInfo.isRepeating = isRepeating;
    }

    public static boolean getIsRepeating() {
        return isRepeating;
    }

    public static boolean isSpacePressed() {
        return SpacePressed;
    }

    public static void setSpacePressed(boolean spacePressed) {
        SpacePressed = spacePressed;
    }

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
        UserLayTimePerStep = layTimePerStep;
    }

    public static void setUserLayTimePerStep(int userLayTimePerStep) {
        MyRepeaterInfo.UserLayTimePerStep = userLayTimePerStep;
    }

    public static int getUserLayTimePerStep() {
        return UserLayTimePerStep;
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
