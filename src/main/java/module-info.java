module com.example.courseproject2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.courseproject2 to javafx.fxml;
    exports com.example.courseproject2;
}