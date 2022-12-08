module com.example.myjavafxdemo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;
    requires jnativehook;


    opens com.example.myjavafxdemo2 to javafx.fxml;
    exports com.example.myjavafxdemo2; // 用于导出包
    exports com.example.myjavafxdemo2.mousedemo;
    opens com.example.myjavafxdemo2.mousedemo to javafx.fxml;
    exports com.example.myjavafxdemo2.mousedemo.somedemo;
    opens com.example.myjavafxdemo2.mousedemo.somedemo to javafx.fxml;
    exports com.example.myjavafxdemo2.mousedemo.learnJnativemousehook;
    opens com.example.myjavafxdemo2.mousedemo.learnJnativemousehook to javafx.fxml;
//    opens com.example.myjavafxdemo2.robot to javafx.fxml;
//    exports com.example.myjavafxdemo2.robot;
}