package com.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.springai.advisor.LoggingAdvisor;

@Service
public class ChatService3 {

    private final ChatClient chatClient;
    private final LoggingAdvisor loggingAdvisor;

    public ChatService3(ChatClient chatClient,
                       LoggingAdvisor loggingAdvisor) {
        this.chatClient = chatClient;
        this.loggingAdvisor = loggingAdvisor;
    }

    public String ask(String question) {

        return chatClient.prompt()

                .advisors(loggingAdvisor)

                .user(question)

                .call()

                .content();
    }
}