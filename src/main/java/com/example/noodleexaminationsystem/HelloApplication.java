package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.User.Gender;
import com.example.noodleexaminationsystem.User.User;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;

public class HelloApplication extends Application {
    static Stage mainStage;
    static CoursePlan mainCoursePlan;
    static User mainUser;
    //______________________________________________________ getter/setter_________________________________________________________


    //______________________________________________________ methods _________________________________________________________
    public static void setScene(String fxmlFile){
        try {
            Parent root = FXMLLoader.load(HelloApplication.class.getResource(fxmlFile));
            Scene scene = new Scene(root);
            HelloApplication.mainStage.setScene(scene);
            HelloApplication.mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        try {
            LocalDate date = LocalDate.parse("2007-12-03");
            User admin = User.signUp("admin","admin","admin","admin","jfeij","src/main/resources/images/test_1_profile.jpg",date,"FEMALE");
            Course.addCourse("OOP");
            Course.addCourse("Ds");
            User teacher = User.signUp("teacher","paoi","ijf","ifje","ejia","fie",date ,"female");
            CoursePlan coursePlan = CoursePlan.addCoursePlan("OOP","shit",teacher,Exam.createExam("iei", LocalDateTime.now(),LocalDateTime.of(2023, Month.JANUARY, 1, 12, 0)),date,"fj");
            CoursePlan coursePlan1 = CoursePlan.addCoursePlan("OOP","shit1",teacher,Exam.createExam("iei",LocalDateTime.now(),LocalDateTime.of(2023, Month.JANUARY, 1, 12, 0)),date,"fj");
            CoursePlan coursePlan2 = CoursePlan.addCoursePlan("OOP","shit2",teacher,Exam.createExam("iei",LocalDateTime.now(),LocalDateTime.of(2023, Month.JANUARY, 1, 12, 0)),date,"fj");
            admin.getStudentcoursePlans().add(coursePlan);
            admin.getStudentcoursePlans().add(coursePlan1);
            admin.getStudentcoursePlans().add(coursePlan2);
            admin.getTeacherCourses().put(coursePlan.getName(),coursePlan);
            admin.getTeacherCourses().put(coursePlan.getName(),coursePlan);
            admin.getArchivedCoursePlans(date).add(coursePlan);

            mainCoursePlan=coursePlan;

            //first scene
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            mainStage.setScene(new Scene(fxmlLoader.load()));
//            mainStage.setMaxWidth(1600);
//            mainStage.setMaxHeight(900);
            mainStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}