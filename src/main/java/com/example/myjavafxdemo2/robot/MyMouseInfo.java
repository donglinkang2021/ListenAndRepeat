package com.example.myjavafxdemo2.robot;

public class MyMouseInfo {

    //  枚举类，用于表示鼠标的动作类型
    public enum ActionType {
        STOP, // 停止
        MOVE,
        LEFT_CLICK, RIGHT_CLICK, MIDDLE_CLICK,
        DRAG, // 拖拽
        WHEEL // 滚轮
    }

    private static int beginX;
    private static int beginY;
    private static int endX;
    private static int endY;
    private static int wheelAmount;
    private static ActionType mouseAction = ActionType.STOP;
    private static boolean isLeftPressed = false;
    //  endregion

    public static void setMouseAction(ActionType mouseAction) {
        MyMouseInfo.mouseAction = mouseAction;
    }

    public static ActionType getActionType() {
        return mouseAction;
    }

    public static void setBeginPosition(int x, int y) {
        beginX = x;
        beginY = y;
    }

    public static void setEndPosition(int x, int y) {
        endX = x;
        endY = y;
    }

    public static int getBeginX() {
        return beginX;
    }

    public static int getBeginY() {
        return beginY;
    }

    public static int getEndX() {
        return endX;
    }

    public static int getEndY() {
        return endY;
    }

    public static void setWheelAmount(int wheelAmount) {
        MyMouseInfo.wheelAmount = wheelAmount;
    }

    public static int getWheelAmount() {
        return wheelAmount;
    }

    public static void setLeftPressed(boolean leftPressed) {
        isLeftPressed = leftPressed;
    }

    public static boolean isLeftPressed() {
        return isLeftPressed;
    }

    public static void setLeftReleased(boolean leftReleased) {
        isLeftPressed = !leftReleased;
    }

    public static boolean isLeftReleased() {
        return !isLeftPressed;
    }

}
