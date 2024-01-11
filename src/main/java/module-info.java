module com.example.noodleexaminationsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.noodleexaminationsystem to javafx.fxml;
    exports com.example.noodleexaminationsystem;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.xml.crypto;
    requires java.desktop;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.datatype.jsr310;
    opens com.example.noodleexaminationsystem.User to com.fasterxml.jackson.databind;
    opens com.example.noodleexaminationsystem.Course to com.fasterxml.jackson.databind;
    opens com.example.noodleexaminationsystem.Question to com.fasterxml.jackson.databind;
}