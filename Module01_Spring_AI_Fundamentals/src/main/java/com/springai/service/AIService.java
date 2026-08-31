package com.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final ChatClient chatClient;

    public AIService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    //Completion + Prompt 
    public String askAI(String message) {

        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }
    
    //Chat Completion + Prompt
    public String javaTeacher(String question) {

        return chatClient.prompt()

                .system("""
                        You are a Senior Java Trainer.
                        Explain everything in details with simple examples.
                        Keep answers interview-oriented.
                        """)

                .user(question)

                .call()

                .content();

    }
    
    public String conversation() {

        Prompt prompt = new Prompt(

                new SystemMessage("""
                        You are a Java Trainer.
                        """),

                new UserMessage("What is Spring Boot?"),

                new AssistantMessage("""
                        Spring Boot is a framework used to build Java applications quickly.
                        """),

                new UserMessage("""
                        Explain it with a real project example.
                        """)
        );

        return chatClient.prompt(prompt)
                .call()
                .content();
    }
    
    

}
