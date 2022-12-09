package com.example.myjavafxdemo2.listenerandrepeater;

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

    // region Keyboard
    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        }
        // 之所以放在前面是因为不想记录esc键的退出信息
        System.out.println(UserActionType.KEYPRESS + " (keyPress) " + nativeKeyEvent.getKeyCode() + " (" + NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()) + ")");
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        System.out.println(UserActionType.KEYRELEASE + " (keyRelease) " + nativeKeyEvent.getKeyCode() + " (" + NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()) + ")");
    }

    // endregion

    // region Mouse
    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        // 1: left, 2: right, 3: middle (但是在awt.robot中，1: left, 2: middle, 3: right)
        System.out.println(UserActionType.MOUSEPRESS +" (mousePress) "+ nativeMouseEvent.getButton() + " (btn) " + nativeMouseEvent.getX() + " " + nativeMouseEvent.getY() + " (pos)");
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
        // 1: left, 2: right, 3: middle (但是在awt.robot中，1: left, 2: middle, 3: right)
        System.out.println(UserActionType.MOUSERELEASE + " (mouseRelease) "+nativeMouseEvent.getButton() + " (btn) " + nativeMouseEvent.getX() + " " + nativeMouseEvent.getY() + " (pos)");
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent nativeMouseWheelEvent) {
        System.out.println(UserActionType.MOUSEWHEELMOVE + " (mouseWheelMove) " + nativeMouseWheelEvent.getWheelRotation() + " (deg)");
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

        changeOutputStream();
    }

    private static void changeOutputStream() {
        //         转换控制台输出到文件
        File output = new File("./output.txt");
        try {
            System.setOut(new PrintStream(new FileOutputStream(output)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
