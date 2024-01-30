package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.Question.*;
import com.example.noodleexaminationsystem.User.Result;
import com.example.noodleexaminationsystem.User.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;



public class HelloApplication extends Application {
    private static HashMap<User,HashMap<Exam, VBox >> userPreviousAnswersHashMap = new HashMap<>();
    static Stage mainStage;

    static CoursePlan mainCoursePlan;
    //______________________________________________________ getter/setter _________________________________________________________


    public static HashMap<User, HashMap<Exam, VBox>> getUserPreviousAnswersHashMap() {
        return userPreviousAnswersHashMap;
    }

    public static void setUserPreviousAnswersHashMap(HashMap<User, HashMap<Exam, VBox>> userPreviousAnswersHashMap) {
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
            //----------------------------------------------------------Users----------------------------------------------------------------------------
            User admin = User.signUp("ali", "ali", "Ali", "Hamzeh", "Ali@cse.shirazu.ac.ir", "src/main/resources/images/mainAdmin.png", date, "MALE", "ADMIN");
            User admin1 = User.signUp("Robert", "secondmain", "Robert", "Brown", "RoberBrown@gmail.com", "src/main/resources/images/OIP (2).jpeg", date, "FEMALE", "ADMIN");
            User admifdsn1 = User.signUp("Robert", "secondmain", "Robert", "Brown", "RoberBrown@gmail.com", "src/main/resources/images/OIP (2).jpeg", date, "FEMALE", "ADMIN");
            User adfdsamin1 = User.signUp("Robert", "secondmain", "Robert", "Brown", "RoberBrown@gmail.com", "src/main/resources/images/OIP (2).jpeg", date, "FEMALE", "ADMIN");
            User adfdsavmin1 = User.signUp("Robert", "secondmain", "Robert", "Brown", "RoberBrown@gmail.com", "src/main/resources/images/OIP (2).jpeg", date, "FEMALE", "ADMIN");
            User adrewmin1 = User.signUp("Robert", "secondmain", "Robert", "Brown", "RoberBrown@gmail.com", "src/main/resources/images/OIP (2).jpeg", date, "FEMALE", "ADMIN");
            User admcxzcdsin1 = User.signUp("Robert", "secondmain", "Robert", "Brown", "RoberBrown@gmail.com", "src/main/resources/images/OIP (2).jpeg", date, "FEMALE", "ADMIN");
            User admuytrjin1 = User.signUp("Robert", "secondmain", "Robert", "Brown", "RoberBrown@gmail.com", "src/main/resources/images/OIP (2).jpeg", date, "FEMALE", "ADMIN");
            User adhtremin1 = User.signUp("Robert", "secondmain", "Robert", "Brown", "RoberBrown@gmail.com", "src/main/resources/images/OIP (2).jpeg", date, "FEMALE", "ADMIN");

            User admin2 = User.signUp("Alice", "Alice12345", "Alice", "Cooper", "AliceCooper@gmail.com", "src/main/resources/images/R (1).jpeg", date, "FEMALE", "ADMIN");
            User admin3 = User.signUp("Alice", "Alice12345", "Alice", "Cooper", "AliceCooper@gmail.com", "src/main/resources/images/R (1).jpeg", date, "FEMALE", "ADMIN");
            User admin4 = User.signUp("Alice", "Alice12345", "Alice", "Cooper", "AliceCooper@gmail.com", "src/main/resources/images/R (1).jpeg", date, "FEMALE", "ADMIN");
            User admin5 = User.signUp("Alice", "Alice12345", "Alice", "Cooper", "AliceCooper@gmail.com", "src/main/resources/images/R (1).jpeg", date, "FEMALE", "ADMIN");
            User admin6 = User.signUp("Alice", "Alice12345", "Alice", "Cooper", "AliceCooper@gmail.com", "src/main/resources/images/R (1).jpeg", date, "FEMALE", "ADMIN");
            User teacher1=User.signUp("Shiva" , "Shivi1122" ,"Shiva" , "Zare" , "ShivaZare@gmail.com" ,"src/main/resources/images/R.jpeg",date ,"FEMALE" ,"MEMBER" );
            User teacher2=User.signUp("Setayesh" , "Setayesh1234" ,"Setayesh" , "Saeedi" , "SetayeshSaeedi@gmail.com" ,"src/main/resources/images/OIP.jpeg",date ,"FEMALE" ,"MEMBER" );
            User teacher3=User.signUp("Raha" , "Raha1234" ,"Raha" , "Rahmanian" , "RahaRahmanian@gmail.com" ,"src/main/resources/images/OIP (1).jpeg",date ,"FEMALE" ,"MEMBER" );
            User teacher4=User.signUp("Sina" , "Sina112233" ,"Sina" , "Hakimzadeh" , "SinaHakimzadeh@gmail.com" ,"src/main/resources/images/R.jpeg",date ,"MALE" ,"MEMBER" );
            User teacher5=User.signUp("ADAM" , "Sina112233" ,"Adam" , "Hadid" , "AdamHadid@gmail.com" ,"src/main/resources/images/R.jpeg",date ,"MALE" ,"MEMBER" );
            User teacher6=User.signUp("Hana" , "Sina112233" ,"Hana" , "Rohbi" , "HanaRohbi@gmail.com" ,"src/main/resources/images/R.jpeg",date ,"MALE" ,"MEMBER" );
            User student1 = User.signUp("Alex", "Alex1234", "Alex", "Johnson", "alex.johnson@email.com", "path/to/image1.jpeg", date, "MALE", "MEMBER");
            User student2 = User.signUp("Emily", "Emi5678", "Emily", "Smith", "emily.smith@email.com", "path/to/image2.jpeg", date, "FEMALE", "MEMBER");
            User student3 = User.signUp("Michael", "Mike9012", "Michael", "Brown", "michael.brown@email.com", "path/to/image3.jpeg", date, "MALE", "MEMBER");
            User student4 = User.signUp("Sarah", "Sarah3456", "Sarah", "Davis", "sarah.davis@email.com", "path/to/image4.jpeg", date, "FEMALE", "MEMBER");
            User student5 = User.signUp("John", "John7890", "John", "Miller", "john.miller@email.com", "path/to/image5.jpeg", date, "MALE", "MEMBER");
            User student6 = User.signUp("Linda", "Linda1357", "Linda", "Wilson", "linda.wilson@email.com", "path/to/image6.jpeg", date, "FEMALE", "MEMBER");
            User student7 = User.signUp("Kevin", "Kevin2468", "Kevin", "Moore", "kevin.moore@email.com", "path/to/image7.jpeg", date, "MALE", "MEMBER");
            User student8 = User.signUp("Jessica", "Jessi159", "Jessica", "Taylor", "jessica.taylor@email.com", "path/to/image8.jpeg", date, "FEMALE", "MEMBER");
            User student9 = User.signUp("Mark", "Mark7531", "Mark", "Anderson", "mark.anderson@email.com", "path/to/image9.jpeg", date, "MALE", "MEMBER");
            User student10 = User.signUp("Nancy", "Nancy8642", "Nancy", "Thomas", "nancy.thomas@email.com", "path/to/image10.jpeg", date, "FEMALE", "MEMBER");
            User student11 = User.signUp("Paul", "Paul9753", "Paul", "Jackson", "paul.jackson@email.com", "path/to/image11.jpeg", date, "MALE", "MEMBER");
            User student12 = User.signUp("Laura", "Laura6842", "Laura", "White", "laura.white@email.com", "path/to/image12.jpeg", date, "FEMALE", "MEMBER");
            User student13 = User.signUp("Peter", "Peter1597", "Peter", "Harris", "peter.harris@email.com", "path/to/image13.jpeg", date, "MALE", "MEMBER");
            User student14 = User.signUp("Susan", "Susan3579", "Susan", "Martin", "susan.martin@email.com", "path/to/image14.jpeg", date, "FEMALE", "MEMBER");
            User student15 = User.signUp("Frank", "Frank2460", "Frank", "Garcia", "frank.garcia@email.com", "path/to/image15.jpeg", date, "MALE", "MEMBER");
            User student16 = User.signUp("Betty", "Betty6541", "Betty", "Robinson", "betty.robinson@email.com", "path/to/image16.jpeg", date, "FEMALE", "MEMBER");
            User student17 = User.signUp("Roberto", "Rob12345", "Roberto", "Walker", "robert.walker@email.com", "path/to/image17.jpeg", date, "MALE", "MEMBER");
            User student18 = User.signUp("Karen", "Karen54321", "Karen", "Allen", "karen.allen@email.com", "path/to/image18.jpeg", date, "FEMALE", "MEMBER");
            User student19 = User.signUp("Gary", "Gary13579", "Gary", "Young", "gary.young@email.com", "path/to/image19.jpeg", date, "MALE", "MEMBER");
            User student20 = User.signUp("Diane", "Diane24680", "Diane", "Hernandez", "diane.hernandez@email.com", "path/to/image20.jpeg", date, "FEMALE", "MEMBER");
            //----------------------------------------------------------courses-----------------------------------------------------------------------------------------
            Course.addCourse("OOP");
            Course.addCourse("DataStructure");
            Course.addCourse("DataBase");
            Course.addCourse("SignalAndSystems");
            Course.addCourse("LogicCircuit");
            Course.addCourse("Physics");
            Course.addCourse("Mathematics");
            Course.addCourse("Calculus1");
            Course.addCourse("Calculus2");
            Course.addCourse("EngineeringProbabilityAndStatics");
            Course.addCourse("DiscreteMathematics");
            Course.addCourse("ComputerArchitecture");
            Course.addCourse("ElectronicCircuit");
            Course.addCourse("ArtificialIntelligence");
            Course.addCourse("DifferentialEquation");
            Course.addCourse("OS");
            Course.addCourse("Compiler");
            Course.addCourse("Assembly");
            //-----------------------------------------------------------course plan------------------------------------------------------------------------
            CoursePlan coursePlan = CoursePlan.addCoursePlan("OOP","OOP",teacher2,LocalDate.parse("2023-12-03"),"fj");
            coursePlan.setEnd(LocalDate.parse("2023-12-03"));
            CoursePlan coursePlan1 = CoursePlan.addCoursePlan("DataStructure","DS",teacher2, LocalDate.now(),"fj");
            CoursePlan coursePlan2 = CoursePlan.addCoursePlan("Compiler","COMPILER",teacher2,LocalDate.now(),"fj");
            CoursePlan coursePlan3 = CoursePlan.addCoursePlan("Calculus2","Calculus",teacher4,LocalDate.now(),"fj");
            CoursePlan coursePlan4 = CoursePlan.addCoursePlan("DataStructure","DS",teacher4,LocalDate.now(),"fj");
            CoursePlan coursePlan5 = CoursePlan.addCoursePlan("ArtificialIntelligence","AI",teacher5,LocalDate.now(),"fj");
            CoursePlan coursePlan6 = CoursePlan.addCoursePlan("DataBase","DB",teacher5,LocalDate.now(),"fj");
            CoursePlan coursePlan7 = CoursePlan.addCoursePlan("OOP","OOP",teacher5,LocalDate.now(),"fj");
            CoursePlan coursePlan8 = CoursePlan.addCoursePlan("LogicCircuit","LC",teacher5,LocalDate.now(),"fj");
            CoursePlan coursePlan9 = CoursePlan.addCoursePlan("Compiler","Complier",teacher6,LocalDate.now(),"fj");
            CoursePlan coursePlan10 = CoursePlan.addCoursePlan("OOP","OOP",teacher6,LocalDate.now(),"fj");
            CoursePlan coursePlan11 = CoursePlan.addCoursePlan("OS","AI",teacher6,LocalDate.now(),"fj");
            CoursePlan coursePlan12 = CoursePlan.addCoursePlan("Assembly","ASM",teacher6,LocalDate.now(),"fj");
            CoursePlan coursePlan13 = CoursePlan.addCoursePlan("DiscreteMathematics","AI",teacher3,LocalDate.now(),"fj");
            CoursePlan coursePlan14 = CoursePlan.addCoursePlan("EngineeringProbabilityAndStatics","EngineeringProbabilityAndStatics",teacher3,LocalDate.now(),"fj");
            //-----------------------------------------------------------exams--------------------------------------------------------------------
            LocalDate activeDay = LocalDate.now();
            LocalDateTime activeExamStart = activeDay.atTime(00,00,00);
            LocalDateTime activeExamEnd = activeDay.atTime(23,59,59);
            Exam sampleExam = Exam.createExam(coursePlan,"OOP",activeExamStart,activeExamEnd);
            Exam exam1 = Exam.createExam(coursePlan10, "OOP", activeExamStart, activeExamEnd);
            Exam exam2 = Exam.createExam(coursePlan4, "DataStructure", activeExamStart, activeExamEnd);
            Exam exam3 = Exam.createExam(coursePlan6, "DataBase", activeExamStart, activeExamEnd);
            Exam exam5 = Exam.createExam(coursePlan8, "LogicCircuit", activeExamStart, activeExamEnd);
            Exam exam9 = Exam.createExam(coursePlan3, "Calculus2", activeExamStart, activeExamEnd);
            Exam exam10 = Exam.createExam(coursePlan14, "EngineeringProbabilityAndStatics", activeExamStart, activeExamEnd);
            Exam exam11 = Exam.createExam(coursePlan13, "DiscreteMathematics", activeExamStart, activeExamEnd);
            Exam exam14 = Exam.createExam(coursePlan5, "ArtificialIntelligence", activeExamStart, activeExamEnd);
            Exam exam15 = Exam.createExam(coursePlan11, "OS", activeExamStart, activeExamEnd);
            Exam exam17 = Exam.createExam(coursePlan9, "Compiler", activeExamStart, activeExamEnd);
            Exam exam18 = Exam.createExam(coursePlan12, "Assembly", activeExamStart, activeExamEnd);
            Exam exam19 = Exam.createExam(coursePlan7, "OOP", activeExamStart, activeExamEnd);

            //------------------------------------------------results-------------------------------------------------------
            Result result = Result.addResult(admin,sampleExam);
            Result result1 = Result.addResult(admin2,sampleExam);
//            admin.getStudentcoursePlans().add(coursePlan);
//            admin.getStudentcoursePlans().add(coursePlan1);
//            admin.getStudentcoursePlans().add(coursePlan2);
            //-----------------------------------------AddStudent-------------------------------------------------------
            coursePlan.addStudentToCoursePlane(student1.getUsername());
            coursePlan.addStudentToCoursePlane(student2.getUsername());
            coursePlan1.addStudentToCoursePlane(student1.getUsername());
            coursePlan1.addStudentToCoursePlane(student3.getUsername());
            coursePlan1.addStudentToCoursePlane(student8.getUsername());
            coursePlan1.addStudentToCoursePlane(student10.getUsername());
            coursePlan2.addStudentToCoursePlane(student17.getUsername());
            coursePlan2.addStudentToCoursePlane(student12.getUsername());
            coursePlan3.addStudentToCoursePlane(student16.getUsername());
            coursePlan4.addStudentToCoursePlane(student18.getUsername());
            coursePlan4.addStudentToCoursePlane(student19.getUsername());
            coursePlan4.addStudentToCoursePlane(student10.getUsername());
            coursePlan5.addStudentToCoursePlane(student11.getUsername());
            coursePlan5.addStudentToCoursePlane(student12.getUsername());
            coursePlan6.addStudentToCoursePlane(student13.getUsername());
            coursePlan6.addStudentToCoursePlane(student5.getUsername());
            coursePlan6.addStudentToCoursePlane(student12.getUsername());
            coursePlan6.addStudentToCoursePlane(student18.getUsername());
            coursePlan6.addStudentToCoursePlane(student1.getUsername());
            coursePlan6.addStudentToCoursePlane(student6.getUsername());
            coursePlan6.addStudentToCoursePlane(student9.getUsername());
            coursePlan7.addStudentToCoursePlane(student7.getUsername());
            coursePlan7.addStudentToCoursePlane(student9.getUsername());
            coursePlan8.addStudentToCoursePlane(student6.getUsername());
            coursePlan8.addStudentToCoursePlane(student4.getUsername());
            //----------------------------------------------------------------------------------------------------------------------
            admin.getTeacherCourses().put(coursePlan.getName(), coursePlan);
            admin.getTeacherCourses().put(coursePlan.getName(), coursePlan);
            admin.getArchivedCoursePlans(date).add(coursePlan);
            //--------------------------------------------AddQuestionToExam------------------------------------------------------------
//           adding questions to exam for test
            ArrayList<String> answers = new ArrayList<>();answers.add("shit");answers.add("shit2");answers.add("shit3");answers.add("shit4");
            SingleAnswer question = new SingleAnswer(DataBase.getCourses().get("OOP"),"this is test",admin,3,answers );
            LongAnswer question2 = new LongAnswer(DataBase.getCourses().get("OOP"),"this is test",admin,"this is the shity answer");
            sampleExam.addQuestion(question);
            sampleExam.addQuestion(question2);
            result.getAnswers().put(result.getExam().getQuestions().get(0),0);
            result.getAnswers().put(result.getExam().getQuestions().get(1),new LongAnswerStudentAnswer("HORAAAAAAAAAAAAAAAAAAAAA",0));

            //testing question bank page
//            DataBase.getQuestions().get(oop).add(question);
//            DataBase.getQuestions().get(oop).add(question2);
            //______________________________________
//            mainCoursePlan=coursePlan;
            //first scene
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            mainStage.setScene(new Scene(fxmlLoader.load()));
//            mainStage.setMaxWidth(1560);
//            mainStage.setMaxHeight(870);
            mainStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}