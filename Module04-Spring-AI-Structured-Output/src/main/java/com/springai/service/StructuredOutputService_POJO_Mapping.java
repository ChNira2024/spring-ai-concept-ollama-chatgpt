package com.springai.service;


import com.springai.model.Employee;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class StructuredOutputService_POJO_Mapping {

    private final ChatClient chatClient;

    public StructuredOutputService_POJO_Mapping(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public Employee employee() {

        return chatClient.prompt()

                .system("""
                        You are an HR Assistant.

                        Return ONLY valid JSON.
                        """)

                .user("""
                        Generate one Employee.

                        Include

                        name

                        age

                        designation

                        salary
                        """)

                .call()

                .entity(Employee.class);

    }

}