package com.example.myjavafxdemo2.demo.learnJnativemousehook;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseListener;
import org.jnativehook.mouse.NativeMouseMotionListener;

/**
 * https://github.com/kwhat/jnativehook
 */
public class GlobalMouseListenerFromCSDN extends JFrame implements NativeMouseInputListener {

    private final JTextArea info;

    public GlobalMouseListenerFromCSDN() {
        info = new JTextArea();
        // 关闭日志
        Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);

        setTitle("GlobalMouseListener");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);

        info.setEditable(false);
        info.setBackground(new Color(0xFF, 0xFF, 0xFF));
        info.setForeground(new Color(0x00, 0x00, 0x00));
        info.setText("");

        JScrollPane scrollPane = new JScrollPane(info);
        scrollPane.setPreferredSize(new Dimension(375, 125));
        add(scrollPane, BorderLayout.CENTER);

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
            info.append("Error: " + e.getMessage() + "\n");
        }
        GlobalScreen.addNativeMouseListener(this);

        setVisible(true); // show the frame
    }

    /**
     * @see NativeMouseListener#nativeMouseClicked(NativeMouseEvent)
     */
    public void nativeMouseClicked(NativeMouseEvent e) {
        appendDisplay(e.paramString());
    }

    /**
     * @see NativeMouseListener#nativeMousePressed(NativeMouseEvent)
     */
    public void nativeMousePressed(NativeMouseEvent e) {
        appendDisplay(e.paramString());
    }

    /**
     * @see NativeMouseListener#nativeMouseReleased(NativeMouseEvent)
     */
    public void nativeMouseReleased(NativeMouseEvent e) {
        appendDisplay(e.paramString());
    }

    /**
     * @see NativeMouseMotionListener#nativeMouseMoved(NativeMouseEvent)
     */
    public void nativeMouseMoved(NativeMouseEvent e) {
        appendDisplay(e.paramString());
    }

    /**
     * @see NativeMouseMotionListener#nativeMouseDragged(NativeMouseEvent)
     */
    public void nativeMouseDragged(NativeMouseEvent e) {
        appendDisplay(e.paramString());
    }

    private void appendDisplay(final String output) {
        info.append(output + "\n");
        try {
            if (info.getLineCount() > 100) {
                info.replaceRange("", 0, info.getLineEndOffset(info.getLineCount() - 1 - 100));
            }
            info.setCaretPosition(info.getLineStartOffset(info.getLineCount() - 1));
        } catch (BadLocationException ex) {
            info.setCaretPosition(info.getDocument().getLength());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GlobalMouseListenerFromCSDN::new);
        // SwingUtilities 是一个用于线程安全的更新组件的类。
        // invokeLater() 方法将一个 Runnable 对象放到事件队列中，等待事件分发线程来处理。
        // 事件分发线程会调用 Runnable 对象的 run() 方法。
        // 这句话的意思是：在事件分发线程中创建一个 GlobalMouseListener 对象。
    }
}
