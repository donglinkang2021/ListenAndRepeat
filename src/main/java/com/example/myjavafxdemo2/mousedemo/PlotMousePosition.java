package com.example.myjavafxdemo2.mousedemo;

import javax.swing.*;
import java.awt.*;

public class PlotMousePosition {
    // 绘制散点图
    public static void plot(int[][] PosXY) {
        new MyFrame(PosXY);
    }
}

class MyFrame extends JFrame {//JFrame对应窗口
    // 绘制散点图
    private MyPanel mp = null;//定义一个面板
    public MyFrame(int[][] positionXY){
        mp = new MyPanel(positionXY);//初始化面板
        this.add(mp);//把面板放入窗口
        this.setTitle("散点图");
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());//设置全屏
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点击窗口的×，关闭程序
        // 设置按键退出 exit on ESCAPE
        this.getRootPane().registerKeyboardAction(
                e -> System.exit(0),
                KeyStroke.getKeyStroke("ESCAPE"),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.setUndecorated(true); // 设置无边框
        this.setVisible(true);//可以显示
    }
}

class MyPanel extends JPanel{
    private int screenWidth = 800;
    private int screenHeight = 600;
    private int[][] positionXY = null;
    private void setFullScreen(){
        // 设置全屏
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenWidth = d.width;
        this.screenHeight = d.height;
    }
    public MyPanel(int[][] positionXY){

        this.positionXY = positionXY;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);//调用父类的方法完成初始化
        // 画线
        setFullScreen();
        plotXY(g, positionXY);
    }
    private void plotXY(Graphics g, int[][] positionXY) {
        for (int i = 0; i < positionXY.length - 1 ; i++) {
            g.drawOval(positionXY[i][0], positionXY[i][1], 5, 5);
            g.drawLine(positionXY[i][0], positionXY[i][1], positionXY[i+1][0], positionXY[i+1][1]);
        }
    }

}