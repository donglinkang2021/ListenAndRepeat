package com.example.myjavafxdemo2.mousedemo.learnJnativemousehook;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseWheelEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyListener implements Listener{
    public enum MouseAction {
        STOP, // 停止
        MOVE,
        LEFT_CLICK, RIGHT_CLICK, MIDDLE_CLICK,
        DRAG, // 拖拽
        WHEEL // 滚轮
    }
    public static MouseAction mouseAction = MouseAction.STOP;
    public static boolean isLeftPressed = false;
    public static int startX, startY, endX, endY;
    public static int clickCount = 0; // 0: no click, 1: single click, 2: double click
    public static int clickButton = 0; // 1: left, 2: right, 3: middle
    public static int wheelAmount = 0;

    public static int typeKeyCode = 0;
    static File output; // 创建输出文件的引用

    // region Keyboard
    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        typeKeyCode = nativeKeyEvent.getKeyCode();
        System.out.println("KeyPressed: " + typeKeyCode + " (" + NativeKeyEvent.getKeyText(typeKeyCode) + ")");
        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        typeKeyCode = nativeKeyEvent.getKeyCode();
        System.out.println("KeyReleased: " + typeKeyCode + " (" + NativeKeyEvent.getKeyText(typeKeyCode) + ")");
    }

    // endregion

    // region Mouse
    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        startX = nativeMouseEvent.getX();
        startY = nativeMouseEvent.getY();
        clickButton = nativeMouseEvent.getButton();
        if (clickButton == 1) {
            isLeftPressed = true;
        }
//        int button = nativeMouseEvent.getButton(); // 1: left, 2: right, 3: middle (但是在robot中，1: left, 2: middle, 3: right)
        // 需要转换一下
        System.out.println("MousePressed: " + nativeMouseEvent.getButton() + " at " + nativeMouseEvent.getX() + ", " + nativeMouseEvent.getY());
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
        endX = nativeMouseEvent.getX();
        endY = nativeMouseEvent.getY();
        clickButton = nativeMouseEvent.getButton();
        if (clickButton == 1) {
            isLeftPressed = false;
        }
        System.out.println("MouseReleased: " + nativeMouseEvent.getButton() + " at " + nativeMouseEvent.getX() + ", " + nativeMouseEvent.getY());
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent nativeMouseWheelEvent) {
        wheelAmount = nativeMouseWheelEvent.getWheelRotation();
        System.out.println("MouseWheelMoved: " + wheelAmount);

    }

    // endregion


    public static void main(String[] args) {

        Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);

        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        // Construct the example object.
        MyListener example = new MyListener();

        // Add the appropriate listeners.
        GlobalScreen.addNativeMouseListener(example);
        GlobalScreen.addNativeMouseMotionListener(example);
        GlobalScreen.addNativeMouseWheelListener(example);
        GlobalScreen.addNativeKeyListener(example);

//         转换控制台输出到文件
//        output = new File("./output.txt");
//        try {
//            System.setOut(new PrintStream(new FileOutputStream(output)));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
