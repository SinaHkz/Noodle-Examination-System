module com.example.noodleexaminationsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.noodleexaminationsystem to javafx.fxml;
    exports com.example.noodleexaminationsystem;
    requires java.xml.crypto;
    requires java.desktop;
    opens com.example.noodleexaminationsystem.User to com.fasterxml.jackson.databind;
    opens com.example.noodleexaminationsystem.Course to com.fasterxml.jackson.databind;
    opens com.example.noodleexaminationsystem.Question to com.fasterxml.jackson.databind;
}