package com.example.myjavafxdemo2.screendemo;

import java.awt.*;

public class showScreenInfo {
    public static boolean isSingleScreen() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        return gs.length == 1;
    }
    public static void main(String[] args) {

        // 输出屏幕数量用GraphicsEnvironment
//        if (isSingleScreen()) {
//            System.out.println("单屏");
//        }
        test();
    }

    public static void test() {
        // 检查屏幕数量
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        System.out.println("屏幕数量：" + ge.getScreenDevices().length);
        // 创建GraphicsEnvironment对象
        GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();

        // 获取所有屏幕对象
        GraphicsDevice[] devices = g.getScreenDevices();

        for (int i = 0; i < devices.length; i++) {
            GraphicsDevice device = devices[i];
            String str = String.format(
                    "第%s个屏幕信息-->宽:%s,高:%s,ID:%s",
                    i + 1,
                    device.getDisplayMode().getWidth(), // 打印分辨率宽度
                    device.getDisplayMode().getHeight(), // 打印分辨率高度
                    device.getIDstring()
            );
            System.out.println(str);
            // 获取屏幕的尺寸用toolkit
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("尺寸："
                + screenSize.width
                + "x"
                + screenSize.height
        );
    }
}
