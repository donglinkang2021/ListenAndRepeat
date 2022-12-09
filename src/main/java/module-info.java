module com.example.myjavafxdemo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;
    requires jnativehook;


    opens com.example.myjavafxdemo2 to javafx.fxml;
    exports com.example.myjavafxdemo2; // 用于导出包
    exports com.example.myjavafxdemo2.demo;
    opens com.example.myjavafxdemo2.demo to javafx.fxml;
    exports com.example.myjavafxdemo2.demo.somedemo;
    opens com.example.myjavafxdemo2.demo.somedemo to javafx.fxml;
    exports com.example.myjavafxdemo2.demo.learnJnativemousehook;
    opens com.example.myjavafxdemo2.demo.learnJnativemousehook to javafx.fxml;
    exports com.example.myjavafxdemo2.listenerandrepeater;
    opens com.example.myjavafxdemo2.listenerandrepeater to javafx.fxml;
    exports com.example.myjavafxdemo2.filedemo;
    opens com.example.myjavafxdemo2.filedemo to javafx.fxml;
//    opens com.example.myjavafxdemo2.robot to javafx.fxml;
//    exports com.example.myjavafxdemo2.robot;
}