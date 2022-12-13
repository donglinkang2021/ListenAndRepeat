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

    static MyListener example;
    private static boolean isListening = false;

    public static String listenerContent = "";

    public static void setIsListening(boolean isListening) {
        MyListener.isListening = isListening;
    }

    public static boolean getIsListening() {
        return isListening;
    }

    public static void setListenerContent(String listenedContent) {
        MyListener.listenerContent = listenedContent;
    }

    public static String getListenerContent() {
        return listenerContent;
    }

    // region Keyboard
    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
//        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
//            try {
//                GlobalScreen.unregisterNativeHook();
//                isListening = false;
//            } catch (NativeHookException nativeHookException) {
//                nativeHookException.printStackTrace();
//            }
//        }
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            setIsListening(false);
        }
        if (!getIsListening()) { // 这一个语句交由外界控制监听器的状态
            stopListen();
        }
        // 之所以放在前面是因为不想记录esc键的退出信息
        String str = UserActionType.KEYPRESS + " (keyPress) " + nativeKeyEvent.getKeyCode() + " (" + NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()) + ")";
        recordListenedInfo(str);
    }


    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        String str = UserActionType.KEYRELEASE + " (keyRelease) " + nativeKeyEvent.getKeyCode() + " (" + NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()) + ")";
        recordListenedInfo(str);
    }

    // endregion

    // region Mouse
    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        // 1: left, 2: right, 3: middle (但是在awt.robot中，1: left, 2: middle, 3: right)
        String str = UserActionType.MOUSEPRESS +" (mousePress) "+ nativeMouseEvent.getButton() + " (btn) " + nativeMouseEvent.getX() + " " + nativeMouseEvent.getY() + " (pos)";
        recordListenedInfo(str);
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
        // 1: left, 2: right, 3: middle (但是在awt.robot中，1: left, 2: middle, 3: right)
        String str = UserActionType.MOUSERELEASE + " (mouseRelease) "+nativeMouseEvent.getButton() + " (btn) " + nativeMouseEvent.getX() + " " + nativeMouseEvent.getY() + " (pos)";
        recordListenedInfo(str);
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent nativeMouseWheelEvent) {
        String str = UserActionType.MOUSEWHEELMOVE + " (mouseWheelMove) " + nativeMouseWheelEvent.getWheelRotation() + " (deg)";
        recordListenedInfo(str);
    }

    // endregion


    public static void startListen() {

        Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);

        try {
            GlobalScreen.registerNativeHook();
            setIsListening(true);
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        // Construct the example object.
        example = new MyListener();

        // Add the appropriate listeners.
        GlobalScreen.addNativeMouseListener(example);
        GlobalScreen.addNativeMouseMotionListener(example);
        GlobalScreen.addNativeMouseWheelListener(example);
        GlobalScreen.addNativeKeyListener(example);

//        changeOutputStream();
    }

    public static void stopListen(){
        try {
            GlobalScreen.removeNativeMouseListener(example);
            GlobalScreen.removeNativeMouseMotionListener(example);
            GlobalScreen.removeNativeMouseWheelListener(example);
            GlobalScreen.removeNativeKeyListener(example);
            GlobalScreen.unregisterNativeHook();
            setIsListening(false);
        } catch (NativeHookException nativeHookException) {
            nativeHookException.printStackTrace();
        }
    }

    public static void main(String[] args) {
        startListen();
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

    // 记录监听到的信息
    public static void recordListenedInfo(String str) {
//        System.out.println(str);
        setListenerContent(getListenerContent() + str + "\n");
//        setListenedContent(str);
    }
}
