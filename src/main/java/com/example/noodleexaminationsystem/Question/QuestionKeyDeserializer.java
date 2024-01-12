package com.example.noodleexaminationsystem.Question;

import com.example.noodleexaminationsystem.Question.Question;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import java.io.IOException;

public class QuestionKeyDeserializer extends KeyDeserializer {
    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        // Implement your deserialization logic here based on your serialization strategy
        // For simplicity, assuming the key is the question ID (replace this with your actual logic)
        return null;
    }
}
