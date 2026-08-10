package com.springai.service;

import com.springai.model.Employee;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class OutputConverterService {

    private final ChatClient chatClient;

    public OutputConverterService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public Employee generateEmployee() {

        return chatClient.prompt()

                .system("""
                        You are an HR Assistant.

                        Return ONLY valid JSON.

                        Never return Markdown.

                        Never explain anything.
                        """)

                .user("""
                        Generate one employee.

                        Required Fields

                        name

                        age

                        designation

                        salary
                        """)

                .call()

                .entity(Employee.class);

    }
    
    public Employee generateSchema() {

        return chatClient.prompt()

                .system("""
                        Return ONLY valid JSON.
                        """)

                .user("""
                        Generate one employee.
                        """)

                .call()

                .entity(Employee.class);

    }

}