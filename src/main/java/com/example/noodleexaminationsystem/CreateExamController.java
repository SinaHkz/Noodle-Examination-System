package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.User.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class CreateExamController implements Initializable {
    CoursePlan coursePlan;
    User user;
    @FXML
    TextField ExamTitle;
    @FXML
    DatePicker StartDate;
    @FXML
    DatePicker EndDate;
    @FXML
    private ComboBox<Integer> HourStartCombo;
    @FXML
    private ComboBox<Integer> MinuteStartCombo;
    @FXML
    private ComboBox<Integer> HourEndCombo;
    @FXML
    private ComboBox<Integer> MinuteEndCombo;



    public void setBackButton() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CoursePage.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            // Now that the FXML is loaded, get the controller and set the data
            CoursePageController coursePageController = loader.getController();
            coursePageController.coursePlan = this.coursePlan;
            coursePageController.user = this.user;
            coursePageController.setCoursePlanPage(this.coursePlan);
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void setCreateExamButton() {
        HourStartCombo.setStyle("-fx-border-color: #096dde");
        HourEndCombo.setStyle("-fx-border-color: #096dde");
        MinuteEndCombo.setStyle("-fx-border-color: #096dde");
        MinuteStartCombo.setStyle("-fx-border-color: #096dde");
        StartDate.setStyle("-fx-border-color: #096dde");
        EndDate.setStyle("-fx-border-color: #096dde");

        boolean flag = false;
        StartDate.setStyle("-fx-border-color: #096dde");
        EndDate.setStyle("-fx-border-color: #096dde");

        if (ExamTitle.getText().isEmpty()) {
            ExamTitle.setStyle("-fx-prompt-text-fill: red");
            flag = true;
        }


        if (StartDate.getValue() == null) {
            StartDate.setStyle("-fx-border-color: red");
            flag = true;
        }
        if (EndDate.getValue() == null) {
            EndDate.setStyle("-fx-border-color: red");
            flag = true;
        }
        if (StartDate.getValue() == null) {
            StartDate.setStyle("-fx-border-color: red");
            flag = true;
        }
        if (EndDate.getValue() == null) {
            EndDate.setStyle("-fx-border-color: red");
            flag = true;
        }
        if (HourStartCombo.getSelectionModel().getSelectedItem() == null) {
            HourStartCombo.setStyle("-fx-border-color: red");
            flag = true;
        }
        if (MinuteStartCombo.getSelectionModel().getSelectedItem() == null) {
            MinuteStartCombo.setStyle("-fx-border-color: red");
            flag = true;
        }
        if (HourEndCombo.getSelectionModel().getSelectedItem() == null) {
            HourEndCombo.setStyle("-fx-border-color: red");
            flag = true;
        }
        if (MinuteEndCombo.getSelectionModel().getSelectedItem() == null) {
            MinuteEndCombo.setStyle("-fx-border-color: red");
            flag = true;
        }

        if (flag) return;
        Integer hourStart = HourStartCombo.getValue();
        Integer minuteStart = MinuteStartCombo.getValue();
        Integer hourEnd = HourEndCombo.getValue();
        Integer minuteEnd = MinuteEndCombo.getValue();
        if (hourStart == null || minuteStart == null || hourEnd == null || minuteEnd == null) {
            //error handdeling
            return;
        }

        LocalTime startTime = LocalTime.of(hourStart, minuteStart);
        LocalTime endTime = LocalTime.of(hourEnd, minuteEnd);
        if (StartDate.getValue() != null && EndDate.getValue() != null) {
            LocalDateTime startDateTime = LocalDateTime.of(StartDate.getValue(), startTime);
            LocalDateTime endDateTime = LocalDateTime.of(EndDate.getValue(), endTime);
            Exam exam = Exam.createExam(this.coursePlan,ExamTitle.getText(), startDateTime, endDateTime);
        }
        //going to course page
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CoursePage.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            // Now that the FXML is loaded, get the controller and set the data
            CoursePageController coursePageController = loader.getController();
            coursePageController.coursePlan = this.coursePlan;
            coursePageController.user = this.user;
            coursePageController.setCoursePlanPage(this.coursePlan);
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < 24; i++) {
            HourStartCombo.getItems().add(i);
            HourEndCombo.getItems().add(i);
        }
        for (int i = 0; i < 60; i++) {
            MinuteStartCombo.getItems().add(i);
            MinuteEndCombo.getItems().add(i);
        }
    }
}
