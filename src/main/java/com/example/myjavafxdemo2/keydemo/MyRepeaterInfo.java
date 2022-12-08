package com.example.myjavafxdemo2.keydemo;

import java.awt.*;

public class MyRepeaterInfo {
    private static int layTimePerStep = 1500;
    private static int loops = 1;

    private static int layTimePerLoop = 1000;

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
