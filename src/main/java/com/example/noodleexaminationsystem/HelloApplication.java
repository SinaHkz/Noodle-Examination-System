package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.Direct.Direct;
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
            User teacher3=User.signUp("raha" , "raha1234" ,"Raha" , "Rahmanian" , "RahaRahmanian@gmail.com" ,"src/main/resources/images/OIP (1).jpeg",date ,"FEMALE" ,"MEMBER" );
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
            Course.addCourse("Data Structure");
            Course.addCourse("Data Base");
            Course.addCourse("Signal And Systems");
            Course.addCourse("Logic Circuit");
            Course.addCourse("Physics");
            Course.addCourse("Mathematics");
            Course.addCourse("Calculus1");
            Course.addCourse("Calculus2");
            Course.addCourse("Computer Lab");
            Course.addCourse("Discrete Mathematics");
            Course.addCourse("Computer Architecture");
            Course.addCourse("Electronic Circuit");
            Course.addCourse("AI");
            Course.addCourse("Differential Equation");
            Course.addCourse("OS");
            Course.addCourse("Compiler");
            Course.addCourse("Assembly");
            //-----------------------------------------------------------course plan------------------------------------------------------------------------
            CoursePlan coursePlan = CoursePlan.addCoursePlan("OOP","OOP",teacher2,LocalDate.parse("2023-12-03"),"fj");
            coursePlan.setEnd(LocalDate.parse("2023-12-03"));
            CoursePlan coursePlan1 = CoursePlan.addCoursePlan("Data Structure","DS",teacher2, LocalDate.now(),"fj");
            CoursePlan coursePlan2 = CoursePlan.addCoursePlan("Compiler","COMPILER",teacher2,LocalDate.now(),"fj");
            CoursePlan coursePlan3 = CoursePlan.addCoursePlan("Calculus2","Calculus",teacher4,LocalDate.now(),"fj");
            CoursePlan coursePlan4 = CoursePlan.addCoursePlan("Data Structure","DS",teacher4,LocalDate.now(),"fj");
            CoursePlan coursePlan5 = CoursePlan.addCoursePlan("AI","AI",teacher5,LocalDate.now(),"fj");
            CoursePlan coursePlan6 = CoursePlan.addCoursePlan("Data Base","DB",teacher5,LocalDate.now(),"fj");
            CoursePlan coursePlan7 = CoursePlan.addCoursePlan("OOP","OOP",teacher5,LocalDate.now(),"fj");
            CoursePlan coursePlan8 = CoursePlan.addCoursePlan("Logic Circuit","LC",teacher5,LocalDate.now(),"fj");
            CoursePlan coursePlan9 = CoursePlan.addCoursePlan("Compiler","Compiler",teacher6,LocalDate.now(),"fj");
            CoursePlan coursePlan10 = CoursePlan.addCoursePlan("OOP","OOP",teacher6,LocalDate.now(),"fj");
            CoursePlan coursePlan11 = CoursePlan.addCoursePlan("OS","AI",teacher6,LocalDate.now(),"fj");
            CoursePlan coursePlan12 = CoursePlan.addCoursePlan("Assembly","ASM",teacher6,LocalDate.now(),"fj");
            CoursePlan coursePlan13 = CoursePlan.addCoursePlan("Discrete Mathematics","Discrete Mathematics",teacher3,LocalDate.now(),"fj");

            CoursePlan coursePlan14 = CoursePlan.addCoursePlan("Computer Lab","Computer Lab",teacher3,LocalDate.now(),"/Users/raha/Desktop/Noodle-Examination-System/src/main/resources/images/chizi baraye khordan.jpeg");
            //-----------------------------------------------------------exams--------------------------------------------------------------------
            LocalDate activeDay = LocalDate.now();
            LocalDateTime activeExamStart = activeDay.atTime(00,00,00);
            LocalDateTime activeExamEnd = activeDay.atTime(23,59,59);
            Exam sampleExam = Exam.createExam(coursePlan,"OOP",activeExamStart,activeExamEnd);
            Exam exam1 = Exam.createExam(coursePlan10, "OOP", activeExamStart, activeExamEnd);
            Exam exam2 = Exam.createExam(coursePlan4, "Data Structure", activeExamStart, activeExamEnd);
            Exam exam3 = Exam.createExam(coursePlan6, "Data Base", activeExamStart, activeExamEnd);
            Exam exam5 = Exam.createExam(coursePlan8, "Logic Circuit", activeExamStart, activeExamEnd);
            Exam exam9 = Exam.createExam(coursePlan3, "Calculus2", activeExamStart, activeExamEnd);
            Exam exam10 = Exam.createExam(coursePlan14, "Computer Lab", activeExamStart, activeExamEnd);
            Exam exam11 = Exam.createExam(coursePlan13, "Discrete Mathematics", activeExamStart, activeExamEnd);
            Exam exam14 = Exam.createExam(coursePlan5, "AI", activeExamStart, activeExamEnd);
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
            ArrayList<String> oopAnswers = new ArrayList<>();
            oopAnswers.add("Inheritance");
            oopAnswers.add("Encapsulation");
            oopAnswers.add("Polymorphism");
            oopAnswers.add("Abstraction");
            SingleAnswer oopQuestion = new SingleAnswer(DataBase.getCourses().get("OOP"),
                    "Which OOP concept is primarily concerned with hiding the internal state and requiring all interaction to be performed through an object's methods?", admin, 2, oopAnswers);
            // Multiple-choice question
            ArrayList<String> aiMCQAnswers = new ArrayList<>();
            aiMCQAnswers.add("Natural Language Processing");
            aiMCQAnswers.add("Machine Learning");
            aiMCQAnswers.add("Robotics");
            aiMCQAnswers.add("Expert Systems");
            SingleAnswer aiMCQQuestion = new SingleAnswer(DataBase.getCourses().get("ArtificialIntelligence"),
                    "Which branch of AI involves making computers understand and process human language?", admin, 1, aiMCQAnswers);

// Long answer question
            LongAnswer aiLAQuestion = new LongAnswer(DataBase.getCourses().get("ArtificialIntelligence"),
                    "Define 'Machine Learning' and its importance in the field of AI.", admin, "Machine Learning is a subset of AI focused on algorithms that allow computers to learn and make decisions from data. It's crucial for developing predictive models and autonomous systems.");
            // Multiple-choice question
            ArrayList<String> asmMCQAnswers = new ArrayList<>();
            asmMCQAnswers.add("High-level language");
            asmMCQAnswers.add("Low-level language");
            asmMCQAnswers.add("Object-oriented language");
            asmMCQAnswers.add("Scripting language");
            SingleAnswer asmMCQQuestion = new SingleAnswer(DataBase.getCourses().get("Assembly"),
                    "Assembly language is considered as which type of programming language?", admin, 2, asmMCQAnswers);

// Long answer question
            LongAnswer asmLAQuestion = new LongAnswer(DataBase.getCourses().get("Assembly"),
                    "Explain the main difference between assembly language and machine language.", admin, "Assembly language is a low-level, human-readable language, whereas machine language is a set of binary codes directly executed by a computer's CPU.");
            // Multiple-choice question
            ArrayList<String> lcMCQAnswers = new ArrayList<>();
            lcMCQAnswers.add("AND gate");
            lcMCQAnswers.add("OR gate");
            lcMCQAnswers.add("NOR gate");
            lcMCQAnswers.add("XOR gate");
            SingleAnswer lcMCQQuestion = new SingleAnswer(DataBase.getCourses().get("Logic Circuit"),
                    "Which logic gate outputs true only when both inputs are different?", admin, 4, lcMCQAnswers);

// Long answer question
            LongAnswer lcLAQuestion = new LongAnswer(DataBase.getCourses().get("Logic Circuit"),
                    "Describe the operation of a flip-flop circuit.", admin, "A flip-flop is a bistable circuit, having two stable states which can be used to store state information.");
            // Multiple-choice question
            ArrayList<String> dbMCQAnswers = new ArrayList<>();
            dbMCQAnswers.add("Primary Key");
            dbMCQAnswers.add("Foreign Key");
            dbMCQAnswers.add("Unique Key");
            dbMCQAnswers.add("Secondary Key");
            SingleAnswer dbMCQQuestion = new SingleAnswer(DataBase.getCourses().get("Data base"),
                    "Which type of key establishes a link between two tables in a relational database?", admin, 2, dbMCQAnswers);

// Long answer question
            LongAnswer dbLAQuestion = new LongAnswer(DataBase.getCourses().get("Data base"),
                    "Explain the concept of 'Normalization' in relational database design.", admin, "Normalization is a process in database design used to organize data to reduce redundancy and improve data integrity.");
            // Multiple-choice question
            ArrayList<String> dsMCQAnswers = new ArrayList<>();
            dsMCQAnswers.add("First In, First Out (FIFO)");
            dsMCQAnswers.add("Last In, First Out (LIFO)");
            dsMCQAnswers.add("First In, Last Out (FILO)");
            dsMCQAnswers.add("Last In, Last Out (LILO)");
            SingleAnswer dsMCQQuestion = new SingleAnswer(DataBase.getCourses().get("DS"),
                    "A 'Queue' data structure operates on what principle?", admin, 1, dsMCQAnswers);

// Long answer question
            LongAnswer dsLAQuestion = new LongAnswer(DataBase.getCourses().get("DS"),
                    "Describe the difference between a Stack and a Queue.", admin, "A Stack is a LIFO structure where the last element added is the first one to be removed. A Queue is a FIFO structure where the first element added is the first to be removed.");

            LongAnswer oopQuestion2 = new LongAnswer(
                    DataBase.getCourses().get("OOP"),
                    "Explain the concept and benefits of using interfaces in Object-Oriented Programming.",
                    admin,
                    "Interfaces allow for the separation of definition and implementation, enhancing modularity, flexibility, and reusability in software design."
            );

// Example for Data Structures Course
            LongAnswer dsQuestion = new LongAnswer(
                    DataBase.getCourses().get("Data Structure"),
                    "Describe how a binary search tree (BST) works and its average-case time complexity for search operations.",
                    admin,
                    "A BST is a tree data structure where each node has at most two children. The left child's value is less than the parent's, and the right child's value is greater. Average-case complexity for search is O(log n)."
            );

// Example for Database Course
            LongAnswer dbQuestion = new LongAnswer(
                    DataBase.getCourses().get("Data Base"),
                    "Explain the concept of 'Normalization' in relational databases.",
                    admin,
                    "Normalization is a process of organizing data to minimize redundancy. It involves dividing large tables into smaller, less redundant ones, linked by relationships, thereby improving database efficiency and integrity."
            );
            LongAnswer oopQuestion1 = new LongAnswer(
                    DataBase.getCourses().get("OOP"),
                    "Explain the concept of encapsulation in OOP and how it contributes to the robustness of a software application.",
                    admin,
                    "Encapsulation in OOP is the bundling of data with the methods that operate on that data. It restricts direct access to some of an object's components, which is a means of preventing accidental interference and misuse of the methods and data. Encapsulation ensures a controlled and safe interaction with the object's properties and represents a key aspect of robust software design."
            );
            LongAnswer oopQuestion4 = new LongAnswer(
                    DataBase.getCourses().get("OOP"),
                    "What are design patterns in OOP, and why are they important? Give an example of a commonly used design pattern.",
                    admin,
                    "Design patterns are typical solutions to common problems in software design. They represent best practices used by experienced object-oriented software developers. Design patterns solve issues in a way that minimizes the chance of introducing new problems. One common design pattern is the Singleton, which ensures a class has only one instance and provides a global point of access to it. It’s often used for things like managing connections to a database."
            );
            LongAnswer oopQuestion3 = new LongAnswer(
                    DataBase.getCourses().get("OOP"),
                    "Describe polymorphism in OOP. How does it enhance software design?",
                    admin,
                    "Polymorphism in OOP allows objects of different classes to be treated as objects of a common superclass. It enables a single interface to represent different underlying forms (data types). This is achieved through method overriding and interface implementation. Polymorphism simplifies code maintenance and improves reusability by allowing the same interface to be used on different underlying forms, leading to more flexible and scalable software design."
            );
            LongAnswer aiQuestion4 = new LongAnswer(
                    DataBase.getCourses().get("AI"),
                    "How might AI influence the development of future technologies? Provide examples.",
                    admin,
                    "AI is expected to be a key driver in future technological advancements. It could lead to significant breakthroughs in healthcare through personalized medicine and early diagnosis, enhance automation in industries with intelligent robotics, and revolutionize transportation with autonomous vehicles. AI is also poised to impact environmental monitoring, smart cities, and personalized education. However, its development must be managed responsibly to address potential risks and ethical concerns."
            );
            LongAnswer aiQuestion3 = new LongAnswer(
                    DataBase.getCourses().get("AI"),
                    "Discuss the ethical considerations and challenges in AI.",
                    admin,
                    "AI ethics concerns the moral implications and decisions made during the development and use of AI technologies. Key ethical challenges include bias and fairness, where AI systems may perpetuate societal biases; privacy and surveillance, as AI can be used to process large amounts of personal data; and autonomy and job displacement, where AI automation could replace human roles. The development of AI must consider these factors to ensure fair, safe, and responsible use that benefits society while minimizing harm."
            );
            LongAnswer aiQuestion2 = new LongAnswer(
                    DataBase.getCourses().get("AI"),
                    "Explain the concept of neural networks and their significance in deep learning.",
                    admin,
                    "Neural networks are a series of algorithms modeled loosely after the human brain, designed to recognize patterns and interpret sensory data through machine perception, labeling, and clustering. They consist of layers of interconnected nodes, each performing a simple operation on the input data. Deep learning involves neural networks with many layers, enabling the learning of complex patterns in large amounts of data. Deep neural networks are fundamental in advanced AI applications such as computer vision, natural language processing, and autonomous vehicles."
            );
            LongAnswer dbQuestion1 = new LongAnswer(
                    DataBase.getCourses().get("Data Base"),
                    "Describe the relational database model and its advantages over other database models.",
                    admin,
                    "The relational database model organizes data into one or more tables of columns and rows, with a unique key identifying each row. Rows in different tables can be linked using foreign keys. The primary advantages include flexibility, simplicity, data integrity, and the ability to use powerful query languages like SQL. It's efficient for managing large volumes of data and is well-suited for complex queries and reporting."
            );
            LongAnswer dbQuestion2 = new LongAnswer(
                    DataBase.getCourses().get("Data Base"),
                    "Explain the ACID properties of a transaction in a database system.",
                    admin,
                    "ACID stands for Atomicity, Consistency, Isolation, and Durability. Atomicity ensures that all parts of a transaction are completed; if one part fails, the entire transaction fails. Consistency ensures that a transaction only brings the database from one valid state to another. Isolation ensures that concurrent transactions do not affect each other. Durability ensures that once a transaction is committed, it remains so, even in the event of a system failure."
            );
            LongAnswer dsQuestion1 = new LongAnswer(
                    DataBase.getCourses().get("Data Structure"),
                    "Describe the graph data structure and its applications.",
                    admin,
                    "A graph data structure consists of a finite set of nodes (or vertices) and a set of edges connecting these nodes. Graphs can be directed or undirected. They are widely used in various applications like social networks, network broadcasting, pathfinding algorithms in maps, and web crawling. Graphs are pivotal in solving complex computational problems involving relationships and connections."
            );
            LongAnswer dsQuestion2 = new LongAnswer(
                    DataBase.getCourses().get("Data Structure"),
                    "Explain how a hash table works and discuss its performance in terms of complexity.",
                    admin,
                    "A hash table stores key-value pairs and uses a hash function to compute an index into an array of buckets or slots, from which the desired value can be found. Ideally, the hash function assigns each key to a unique bucket, but most hash table designs employ some form of collision resolution. In terms of complexity, hash tables offer constant time O(1) for search, insertion, and deletion on average, but poor hash functions can lead to O(n) time in the worst case."
            );

            exam1.addQuestion(oopQuestion);
            exam1.addQuestion(oopQuestion1);
            exam1.addQuestion(oopQuestion2);
            exam1.addQuestion(oopQuestion4);
            exam1.addQuestion(oopQuestion3);
            exam14.addQuestion(aiLAQuestion);
            exam14.addQuestion(aiMCQQuestion);
            exam14.addQuestion(aiQuestion2);
            exam14.addQuestion(aiQuestion4);
            exam14.addQuestion(aiQuestion3);
            exam3.addQuestion(dbQuestion);
            exam3.addQuestion(dbLAQuestion);
            exam3.addQuestion(dbQuestion1);
            exam3.addQuestion(dbQuestion2);
            exam2.addQuestion(dsQuestion2);
            exam2.addQuestion(dsQuestion1);
            exam2.addQuestion(dsQuestion);
            exam2.addQuestion(dsLAQuestion);
            exam2.addQuestion(dsMCQQuestion);
//            result.getAnswers().put(result.getExam().getQuestions().get(0),0);
//            result.getAnswers().put(result.getExam().getQuestions().get(1),new LongAnswerStudentAnswer("HORAAAAAAAAAAAAAAAAAAAAA",0));

            //testing question bank page
//            DataBase.getQuestions().get(oop).add(question);
//            DataBase.getQuestions().get(oop).add(question2);
            //--------------------------------------------AddQuestionToExam------------------------------------------------------------
//           adding questions to exam for test
            ArrayList<String> answers1 = new ArrayList<>();answers.add("shit");answers.add("shit2");answers.add("shit3");answers.add("shit4");
            SingleAnswer question1 = new SingleAnswer(DataBase.getCourses().get("OOP"),"this is test",admin,3,answers );
            LongAnswer question21 = new LongAnswer(DataBase.getCourses().get("OOP"),"this is test",admin,"this is the shity answer");
            sampleExam.addQuestion(question);
            sampleExam.addQuestion(question2);
            result.getAnswers().put(result.getExam().getQuestions().get(0),0);
            result.getAnswers().put(result.getExam().getQuestions().get(1),new LongAnswerStudentAnswer("HORAAAAAAAAAAAAAAAAAAAAA",0));
            //--------------------------------------------------Direct----------------------------------------------------------------
            Direct direct =Direct.creatNewDirect(teacher2 , teacher3);
            direct.sendMessage(teacher2,"Hi,How's everything?");
            direct.sendMessage(teacher3,"Hello,Fine:)");
            direct.sendMessage(teacher2,"Let's talk about something interesting");
            Direct direct1 =Direct.creatNewDirect(admin , student13);
            direct1.sendMessage(admin, "Your recent assignment submission was excellent.");
            direct1.sendMessage(student13, "Thank you, I appreciate the feedback!");
            Direct direct2 =Direct.creatNewDirect(admin , teacher1);
            direct2.sendMessage(admin, "Reminder: Faculty meeting on Friday at 10 AM.");
            direct2.sendMessage(teacher1, "Acknowledged. I'll be there.");
            Direct direct3 =Direct.creatNewDirect(admin , teacher2);
            direct3.sendMessage(admin, "Please update the course materials for next semester.");
            direct3.sendMessage(teacher2, "Will do. I plan to integrate more practical examples.");
            Direct direct4 =Direct.creatNewDirect(admin , teacher3);
            direct4.sendMessage(admin, "The new curriculum guidelines are ready for review.");
            direct4.sendMessage(teacher3, "Thanks for the update. I will review them by tomorrow.");
            Direct direct5 =Direct.creatNewDirect(admin , teacher4);
            direct5.sendMessage(admin, "Could you assist in organizing the upcoming workshop?");
            direct5.sendMessage(teacher4, "Certainly, I'd be glad to help. Let's discuss the details next week.");
            Direct direct6 =Direct.creatNewDirect(admin , student1);
            direct6.sendMessage(admin, "Your participation in class has been outstanding.");
            direct6.sendMessage(student1, "Thank you! I'm really enjoying the course.");
//            Direct direct7 =Direct.creatNewDirect(teacher2 , teacher3);
//            direct7.sendMessage(teacher2, "Hi, How's everything?");
//            direct7.sendMessage(teacher3, "Hello, Fine :)");
//            direct7.sendMessage(teacher2, "Let's talk about something interesting");
            Direct direct9 =Direct.creatNewDirect(teacher3 , teacher4);
            direct9.sendMessage(teacher3, "Are you available for a joint research meeting this Friday?");
            direct9.sendMessage(teacher4, "Yes, I've been looking forward to our collaboration. See you then.");
            Direct direct10 =Direct.creatNewDirect(teacher3 , teacher5);
            direct10.sendMessage(teacher3, "I have some ideas for our new elective course. When can we discuss?");
            direct10.sendMessage(teacher5, "I'm intrigued! How about a call tomorrow afternoon?");
            Direct direct11 =Direct.creatNewDirect(teacher2 , teacher6);
            direct11.sendMessage(teacher2, "Can you share your notes from yesterday's lecture?");
            direct11.sendMessage(teacher6, "Of course, I'll send them over after class.");
            Direct direct12 =Direct.creatNewDirect(student13 , student11);
            direct12.sendMessage(student13, "Are you ready for the group project meeting?");
            direct12.sendMessage(student11, "Yes, I've prepared the slides we discussed.");
            Direct direct13 =Direct.creatNewDirect(student12 , student13);
            direct13.sendMessage(student12, "Do you want to form a study group for the finals?");
            direct13.sendMessage(student13, "That sounds like a plan. I'll ask a few others to join us.");
            Direct direct14 =Direct.creatNewDirect(student13 , student1);
            direct14.sendMessage(student13, "Can you share the project outline you mentioned in class?");
            direct14.sendMessage(student1, "Sure thing, I'll email it to you later today.");
            Direct direct15 =Direct.creatNewDirect(teacher5, teacher3);
//            direct15.sendMessage(teacher5, "I need advice on handling online teaching challenges.");
//            direct15.sendMessage(teacher3, "Happy to share my experiences. Let's meet over coffee and discuss.");
            Direct direct16 =Direct.creatNewDirect(student12 , teacher3);
            direct16.sendMessage(student12, "I'm having trouble understanding today's lecture on neural networks. Can you recommend some resources?");
            direct16.sendMessage(teacher3, "Sure, I'll send you a list of recommended readings and some tutorial videos that should help clarify the concepts.");
            Direct direct17 =Direct.creatNewDirect(student18 , teacher3);
            direct17.sendMessage(student18, "Could we discuss my project proposal? I'm unsure about the scope.");
            direct17.sendMessage(teacher3, "Absolutely, let's set up a meeting to go over your proposal in detail and make sure it's on the right track.");
            Direct direct18 =Direct.creatNewDirect(student20 , teacher3);
            direct18.sendMessage(student20, "I missed the deadline for the assignment due to illness. Is there a way to make it up?");
            direct18.sendMessage(teacher3, "Health comes first. Let's discuss an extended deadline for you. Please send me a doctor's note, and we'll go from there.");
            Direct direct19 =Direct.creatNewDirect(student7 , student16);
            direct19.sendMessage(student7, "Are you ready for the team presentation next week?");
            direct19.sendMessage(student16, "Almost, let's meet this weekend to finalize it.");
            Direct direct20 =Direct.creatNewDirect(student6 , student7);
            direct20.sendMessage(student6, "Struggling with the math homework. Got any tips?");
            direct20.sendMessage(student7, "Let's meet at the library and work through it together.");
            Direct direct21 =Direct.creatNewDirect(student7 , student5);
            direct21.sendMessage(student7, "Heard you aced the test. Congratulations!");
            direct21.sendMessage(student5, "Thanks! Happy to share my notes if you're interested.");
            Direct direct22 =Direct.creatNewDirect(teacher4 , teacher1);
            direct22.sendMessage(teacher4, "Your insights on blending art and technology were inspiring. Can we collaborate?");
            direct22.sendMessage(teacher1, "I'd love that. Let's brainstorm some project ideas next week.");
            Direct direct23 =Direct.creatNewDirect(student7 , teacher3);
            direct23.sendMessage(student7, "I'm struggling with the concept you explained today. Can we go over it again?");
            direct23.sendMessage(teacher3, "Of course, I'm here to help. How about we meet during my office hours?");
            Direct direct24 =Direct.creatNewDirect(student7 , student10);
            direct24.sendMessage(student7, "Are you going to the science club meeting tonight?");
            direct24.sendMessage(student10, "Yes, wouldn't miss it. The guest speaker sounds fascinating.");
            Direct direct25 =Direct.creatNewDirect(student7 , student9);
            direct25.sendMessage(student7, "Hey, I found the book you were looking for in the library.");
            direct25.sendMessage(student9, "You're a lifesaver! I'll pick it up tomorrow. Thanks!");
            Direct direct26 = Direct.creatNewDirect(student7, student8);
            direct26.sendMessage(student7, "Hey, want to review for the calculus exam together this weekend?");
            direct26.sendMessage(student8, "That'd be great. I'm struggling with integrals, so maybe we can start there.");
            Direct direct27 =Direct.creatNewDirect(teacher2 , student5);
            direct27.sendMessage(teacher2, "Don't forget about tomorrow's lab session.");
            direct27.sendMessage(student5, "Got it, I'll bring the necessary materials.");
            Direct direct28 =Direct.creatNewDirect(teacher2 , student6);
            direct28.sendMessage(teacher2, "Did you understand the concept discussed today in class?");
            direct28.sendMessage(student6, "Partly, let's study together this weekend.");


            //------------------------------------------------------------------------------------------------------------------------
            //testing question bank page
//            DataBase.getQuestions().get(oop).add(question);
//            DataBase.getQuestions().get(oop).add(question2);

            //testing media page
//            coursePlan.addMediaToCourse("first media", "jeijfij");
//            coursePlan.addMediaToCourse("second media", "flijdeufli");



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