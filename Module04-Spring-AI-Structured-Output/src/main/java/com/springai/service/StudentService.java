package com.springai.service;


import com.springai.model.Student;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final ChatClient chatClient;

    public StudentService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public Student student() {

        return chatClient.prompt()

                .system("""
                        You are a College Management AI.

                        Return only valid JSON.
                        """)

                .user("""
                        Generate one student.

                        Fields:

                        name

                        age

                        course

                        percentage
                        """)

                .call()

                .entity(Student.class);

    }

}