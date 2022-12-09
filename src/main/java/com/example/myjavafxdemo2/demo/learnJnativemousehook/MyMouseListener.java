package com.example.myjavafxdemo2.demo.learnJnativemousehook;


import com.example.myjavafxdemo2.listenerandrepeater.MouseListener;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseWheelEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyMouseListener implements MouseListener {
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
    @Override
    public void nativeMouseClicked(NativeMouseEvent e) {
        clickCount = e.getClickCount();
    }
    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
        clickButton = e.getButton();
        if (clickButton == 1) {
            isLeftPressed = true;
        }
        startX = e.getX();
        startY = e.getY();
        System.out.println("Mouse Pressed: " + e.getButton() + " at " + e.getX() + ", " + e.getY());
    }
    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {
        clickButton = e.getButton();
        if (clickButton == 1) {
            isLeftPressed = false;
        }
        endX = e.getX();
        endY = e.getY();
        System.out.println("Mouse Released: " + e.getButton() + " at " + e.getX() + ", " + e.getY());
    }
    @Override
    public void nativeMouseMoved(NativeMouseEvent e) {
//        System.out.println("Mouse Moved: " + e.getX() + ", " + e.getY());
    }
    @Override
    public void nativeMouseDragged(NativeMouseEvent e) {
    }
    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
        wheelAmount = e.getWheelRotation();
//        System.out.println("Mouse Wheel Moved: " + e.getWheelRotation());
    }

//    public void getMousePosition(NativeMouseEvent e) {
//        endX = e.getX();
//        endY = e.getY();
//    }

    // region get MouseAction
//    public static MouseAction getMouseAction() {
//        if (isLeftPressed) {
//            if (startX != endX || startY != endY) {
//                return MouseAction.MOVE;
//            } else {
//                return MouseAction.DRAG;
//            }
//        } else {
//            if (clickButton == 1) {
//                return MouseAction.LEFT_CLICK;
//            } else if (clickButton == 2) {
//                return MouseAction.RIGHT_CLICK;
//            } else if (clickButton == 3) {
//                return MouseAction.MIDDLE_CLICK;
//            }
//        }
//        return MouseAction.STOP;
//    }
    // endregion


    // 按esc键退出


    public static void test2(){
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
        MyMouseListener example = new MyMouseListener();
        // Add the appropriate listeners.
        GlobalScreen.addNativeMouseListener(example);
        GlobalScreen.addNativeMouseMotionListener(example);
        GlobalScreen.addNativeMouseWheelListener(example);

    }

    public static void main(String[] args) {
        test2();
    }
}



