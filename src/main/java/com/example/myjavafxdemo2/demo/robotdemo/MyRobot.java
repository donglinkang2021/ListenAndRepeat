package com.example.myjavafxdemo2.demo.robotdemo;

public class MyRobot {
    // 枚举robot的type

    public static RobotType robotType = RobotType.Sleeper;

    public static void setRobotType(RobotType robotType) {
        MyRobot.robotType = robotType;
    }

    public static RobotType getRobotType() {
        return robotType;
    }

    public static void doRobotAction() {
        switch (robotType) {
            case Sleeper:
                break;
            case Mouse:
                MyMouseAction.doMouseAction();
                break;
            case Keyboard:
                break;
            case Wheel:
                break;
        }
    }

    public static void main(String[] args) {
        MyRobot.setRobotType(RobotType.Mouse);
        MyMouseInfo.setMouseAction(MyMouseInfo.ActionType.MOVE);
        MyMouseInfo.setEndPosition(1453, 718);
        MyRobot.doRobotAction();
    }

}

