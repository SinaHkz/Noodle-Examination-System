package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.Question.*;
import com.example.noodleexaminationsystem.User.Result;
import com.example.noodleexaminationsystem.User.User;
import com.example.noodleexaminationsystem.User.UserType;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;

import java.util.List;


public class HelloApplication extends Application {
    private static HashMap<Exam, VBox > userPreviousAnswersHashMap = new HashMap<>();
    static Stage mainStage;

    static CoursePlan mainCoursePlan;
    //______________________________________________________ getter/setter _________________________________________________________


    public static HashMap<Exam, VBox> getUserPreviousAnswersHashMap() {
        return userPreviousAnswersHashMap;
    }

    public static void setUserPreviousAnswersHashMap(HashMap<Exam, VBox> userPreviousAnswersHashMap) {
        HelloApplication.userPreviousAnswersHashMap = userPreviousAnswersHashMap;
    }

    //______________________________________________________ methods _________________________________________________________
    public static void setScene(String fxmlFile) {
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
            User admin = User.signUp("admin", "admin", "admin", "admin", "jfeij", "src/main/resources/images/test_1_profile.jpg", date, "FEMALE", "ADMIN");
            User admin1 = User.signUp("admin1", "admin", "admin", "admin", "jfeij", "src/main/resources/images/test_1_profile.jpg", date, "FEMALE", "ADMIN");
            User admin2 = User.signUp("admin2", "admin", "admin", "admin", "jfeij", "src/main/resources/images/test_1_profile.jpg", date, "FEMALE", "ADMIN");

            Course.addCourse("OOP");
            DataBase.getUsers().put("admin" ,admin );

            Course.addCourse("Ds");
            User teacher = User.signUp("teacher","teacher","ijf","ifje","ejia","fie",date ,"female", "member");
            CoursePlan coursePlan = CoursePlan.addCoursePlan("OOP","shit",teacher,date,"fj");
            CoursePlan coursePlan1 = CoursePlan.addCoursePlan("OOP","shit1",teacher, date,"fj");
            CoursePlan coursePlan2 = CoursePlan.addCoursePlan("OOP","shit2",teacher,date,"fj");

            //adding exams
            LocalDateTime examStart = date.atTime(12,30,30);
            LocalDateTime examEnd = date.atTime(12,50,30);
            Exam sampleExam = Exam.createExam(coursePlan,"OOP",examStart,examEnd);
            Result result = Result.addResult(admin,sampleExam);
            Result result1 = Result.addResult(admin2,sampleExam);

            LocalDate activeDay = LocalDate.parse("2024-01-28");
            LocalDateTime activeExamStart = activeDay.atTime(10,30,30);
            LocalDateTime activeExamEnd = activeDay.atTime(12,29,00);
            Exam activeExam = Exam.createExam(coursePlan,"activeExam",activeExamStart,activeExamEnd);



            admin.getStudentcoursePlans().add(coursePlan);
            admin.getStudentcoursePlans().add(coursePlan1);
            admin.getStudentcoursePlans().add(coursePlan2);
            admin.getTeacherCourses().put(coursePlan.getName(), coursePlan);
            admin.getTeacherCourses().put(coursePlan.getName(), coursePlan);
            admin.getArchivedCoursePlans(date).add(coursePlan);


//            ______________________________________
//           adding questions to exam for test
            ArrayList<String> answers = new ArrayList<>();answers.add("shit");answers.add("shit2");answers.add("shit3");answers.add("shit4");
            SingleAnswer question = new SingleAnswer(DataBase.getCourses().get("OOP"),"this is test",admin, Choice.FOUR,3,answers );
            LongAnswer question2 = new LongAnswer(DataBase.getCourses().get("OOP"),"this is test",admin,"this is the shity answer");
            sampleExam.addQuestion(question);
            sampleExam.addQuestion(question2);
            activeExam.addQuestion(question);
            activeExam.addQuestion(question2);
            result.getAnswers().put(result.getExam().getQuestions().get(0),0);
            result.getAnswers().put(result.getExam().getQuestions().get(1),new LongAnswerStudentAnswer("HORAAAAAAAAAAAAAAAAAAAAA",0));


            //______________________________________
//            mainCoursePlan=coursePlan;
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
//        LocalDate date = LocalDate.parse("2007-12-03");
//        User admin = User.signUp("admin", "admin", "admin", "admin", "jfeij", "src/main/resources/images/test_1_profile.jpg", date, "FEMALE", "ADMIN");
//        Course.addCourse("OOP");
//        Course.addCourse("Ds");
//        try{
//            DataBase.deserializeDataBase();
//        }catch (Exception JsonProcessingException){
//            JsonProcessingException.printStackTrace();
//        }
        launch();
        //User first = User.signUp("test" , "test" ,"admin", "admin", "jfeij", "src/main/resources/images/test_1_profile.jpg", date, "FEMALE", "MEMBER" );
        //CoursePlan.addCoursePlan("hello" , "hello" , first , date , "ds");
//        try {
//            DataBase.serializeDataBase();
//        }catch (Exception JsonProcessingException){
//            JsonProcessingException.printStackTrace();
//        }
    }
}