package com.springai.service;

import com.springai.model.Employee;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class SchemaService {

    private final ChatClient chatClient;

    public SchemaService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public Employee employee() {

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