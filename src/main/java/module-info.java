module com.example.courseproject2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.courseproject2 to javafx.fxml;
    exports com.example.courseproject2;
    exports main;

    opens model;
}
