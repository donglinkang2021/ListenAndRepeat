package com.example.myjavafxdemo2.demo.firstappdemo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    public enum RobotType {
        None,
        Sleeper, // 休眠
        Mouse, // 鼠标
        Keyboard, // 键盘
        Wheel, // 滚轮 (放在MyMouse里面)
    }
    public static RobotType robotType = RobotType.Sleeper;
    public static boolean isMouseTabSelected = false;
    public static boolean isKeyboardTabSelected = false;
    public static boolean isWheelTabSelected = false;
    public static boolean isSleeperTabSelected = false;

    // region tab select robot type
    @FXML
    protected void onMouseTabSelected() {
        isMouseTabSelected = !isMouseTabSelected;
        if (isMouseTabSelected) {
            robotType = RobotType.Mouse;
            System.out.println("Mouse tab opened");
        }else {
            System.out.println("Mouse tab closed");
        }
    }

    @FXML
    protected void onKeyboardTabSelected() {
        isKeyboardTabSelected = !isKeyboardTabSelected;
        if (isKeyboardTabSelected) {
            robotType = RobotType.Keyboard;
            System.out.println("Keyboard tab opened");
        }else {
            System.out.println("Keyboard tab closed");
        }
    }

    @FXML
    protected void onWheelTabSelected() {
        isWheelTabSelected = !isWheelTabSelected;
        if (isWheelTabSelected) {
            robotType = RobotType.Wheel;
            System.out.println("Wheel tab opened");
        }else {
            System.out.println("Wheel tab closed");
        }
    }

    @FXML
    protected void onSleeperTabSelected() {
        isSleeperTabSelected = !isSleeperTabSelected;
        if (isSleeperTabSelected) {
            robotType = RobotType.Sleeper;
            System.out.println("Sleeper tab opened");
        }else {
            System.out.println("Sleeper tab closed");
        }
    }
    // endregion

    @FXML
    private Label welcomeText;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Current time: " + new java.util.Date());
    }

}