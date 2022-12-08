package com.example.myjavafxdemo2.mousedemo.learnJnativemousehook;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

import java.util.logging.Level;
import java.util.logging.Logger;


public class GlobalMouseWheelListenerExample implements NativeMouseWheelListener {
    public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
        System.out.println("Mouse Wheel Moved: " + e.getWheelRotation());
    }

    public static void main(String[] args) {
        Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            ex.printStackTrace();

            System.exit(1);
        }

        GlobalScreen.addNativeMouseWheelListener(new GlobalMouseWheelListenerExample());
    }
}
