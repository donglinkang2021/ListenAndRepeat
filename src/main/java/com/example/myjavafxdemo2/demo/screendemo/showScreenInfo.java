package com.example.myjavafxdemo2.demo.screendemo;

import java.awt.*;

public class showScreenInfo {
    public static boolean isSingleScreen() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        return gs.length == 1;
    }
    public static void main(String[] args) {
//        test();
//        ScreenSizeExample.main(args);
        MultipleDisplaySizesExample.main(args);
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

// 获得屏幕的尺寸和dpi
class ScreenSizeExample {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        System.out.println("Screen size: " + width + "x" + height);

        // Get screen resolution
        int screenResolution = Toolkit.getDefaultToolkit().getScreenResolution();
        System.out.println("Screen resolution: " + screenResolution + " dpi");
    }
}

// 获得多个屏幕的尺寸和起点
class MultipleDisplaySizesExample {
    public static void main(String[] args) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        for (int i = 0; i < gs.length; i++) {
            GraphicsDevice gd = gs[i];
            System.out.println("分辨率" + (i+1) + ": " + gd.getDisplayMode().getWidth() + "x" + gd.getDisplayMode().getHeight());
            GraphicsConfiguration[] gc = gd.getConfigurations(); // 获取屏幕配置 一个屏幕可能有多个配置
            for (int j = 0; j < gc.length; j++) {
                Rectangle bounds = gc[j].getBounds();
                int width = bounds.width;
                int height = bounds.height;
                int x = bounds.x;
                int y = bounds.y;
                System.out.println("尺寸 " + (i + 1) + ": " + width + "x" + height + " at (" + x + ", " + y + ")");
            }
        }
    }
}
