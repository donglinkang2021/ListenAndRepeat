package com.example.myjavafxdemo2.threademo;

import com.example.myjavafxdemo2.demo0Controller;
import com.example.myjavafxdemo2.listenerandrepeater.MyListener;
import javafx.application.Platform;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.logging.Level;
import java.util.logging.Logger;

public class usePlatform {
    private boolean isPlatformRun = false;

    public void setPlatformRun(boolean platformRun) {
        isPlatformRun = platformRun;
    }

    public boolean getPlatformRun() {
        return isPlatformRun;
    }

    public static void main(String[] args) {
        ThreadListener.addListener();
        new Thread(() -> {
            MyListener.startListen();
            while (true) {
                if(!MyListener.getIsListening()){
                    Thread.currentThread().interrupt(); // 标记线程为中断状态
                    break;
                }
                Platform.runLater(() -> {
                    System.out.println(MyListener.getListenerContent());
                });
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                // 输出监听线程的名称
                System.out.println("线程：" + Thread.currentThread().getName()+"在监听");
            }
            System.out.println("监听结束");
            MyListener.stopListen();
        }).start();
    }

    static class ThreadListener implements NativeKeyListener {

        public static void addListener() {
            Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);

            try {
                GlobalScreen.registerNativeHook();
            }
            catch (NativeHookException ex) {
                System.err.println("There was a problem registering the native hook.");
                System.err.println(ex.getMessage());
                System.exit(1);
            }
            GlobalScreen.addNativeKeyListener(new ThreadListener());
        }

        public static void removeListener() {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
            if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {

            }
        }

        @Override
        public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        }

        @Override
        public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

        }
    }
}
