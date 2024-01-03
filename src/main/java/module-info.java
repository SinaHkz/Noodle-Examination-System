module com.example.noodleexaminationsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.noodleexaminationsystem to javafx.fxml;
    exports com.example.noodleexaminationsystem;
}