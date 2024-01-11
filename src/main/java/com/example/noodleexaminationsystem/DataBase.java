package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Question.Question;
import com.example.noodleexaminationsystem.User.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.image.DataBufferShort;
import java.io.*;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PrivilegedExceptionAction;
import java.util.List;

public class DataBase {
    static Map<String, User> users = new HashMap<>();
    static Map<String, CoursePlan> coursePlans = new HashMap<>();
    static Map<String, Course> courses = new HashMap<>();
    static Map<Course, Question> questions = new HashMap<>();


    public static void setUsers(Map<String, User> users) {
        DataBase.users = users;
    }

    public static Map<String, User> getUsers() {
        return users;
    }

    public static Map<String, CoursePlan> getCoursePlans() {
        return coursePlans;
    }

    public static void setCoursePlans(Map<String, CoursePlan> coursePlans) {
        DataBase.coursePlans = coursePlans;
    }

    public static Map<String, Course> getCourses() {
        return courses;
    }

    public static void setCourses(Map<String, Course> courses) {
        DataBase.courses = courses;
    }

    public static Map<Course, Question> getQuestions() {
        return questions;
    }

    public static void setQuestions(Map<Course, Question> questions) {
        DataBase.questions = questions;
    }

    public DataBase() {
    }

    //----------------------------------------------------------------------
    private static void clearAndWriteToFile(Path filePath, String content, Charset charset) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, charset)) {
            writer.write(content, 0, content.length());

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
    private static String readFromFile(Path filePath,Charset charset){
        String fileContent = "";
        String line;
        try (BufferedReader reader = Files.newBufferedReader(filePath, charset)) {
            while ((line = reader.readLine()) != null) {
                fileContent += line;
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        return  fileContent;
    }

    public static void serializeDataBase() throws JsonProcessingException {
        Charset charset = Charset.forName("US-ASCII");
        String serializedObject1;
        String serializedObject2 , serializedObject3 , serializedObject4;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            serializedObject1 = objectMapper.writeValueAsString(DataBase.courses);
            serializedObject2 = objectMapper.writeValueAsString(DataBase.users);
            serializedObject3 = objectMapper.writeValueAsString(DataBase.questions);
            serializedObject4 = objectMapper.writeValueAsString(DataBase.coursePlans);
        }catch (IOException e){
            e.printStackTrace();
            return;
        }
        Path filePath1 = Paths.get("dataCourses.json");
        Path filepath2 = Paths.get("dataUsers.json");
        Path filepath3 = Paths.get("dataQuestions.json");
        Path filepath4 = Paths.get("dataCoursePlan.json");
        clearAndWriteToFile(filePath1, serializedObject1,charset);
        clearAndWriteToFile(filepath2, serializedObject2,charset);
        clearAndWriteToFile(filepath3, serializedObject3,charset);
        clearAndWriteToFile(filepath4, serializedObject4,charset);

    }
    public static void deserializeDataBase() throws JsonProcessingException {
        Charset charset = Charset.forName("US-ASCII");
        Path filePath1 = Paths.get("dataCoursePlan.json");
        Path filePath2 = Paths.get("dataCourses.json");
        Path filePath3 = Paths.get("dataQuestions.json");
        Path filePath4 = Paths.get("dataUsers.json");
        String deserializedObject1 = DataBase.readFromFile(filePath1,charset);
        String deserializedObject2 = DataBase.readFromFile(filePath2,charset);
        String deserializedObject3 = DataBase.readFromFile(filePath3,charset);
        String deserializedObject4 = DataBase.readFromFile(filePath4,charset);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        DataBase.setCoursePlans(objectMapper.readValue(deserializedObject1,new TypeReference<Map<String ,CoursePlan>>(){}));
        DataBase.setCourses(objectMapper.readValue(deserializedObject2,new TypeReference<Map<String ,Course>>(){}));
        DataBase.setQuestions(objectMapper.readValue(deserializedObject3,new TypeReference<Map<Course ,Question>>(){}));
        DataBase.setUsers(objectMapper.readValue(deserializedObject4,new TypeReference<Map<String ,User>>(){}));

    }
}
