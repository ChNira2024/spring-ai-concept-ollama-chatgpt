package com.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.springai.advisor.LoggingAdvisor;
import com.springai.advisor.SecurityAdvisor;
import com.springai.advisor.TimingAdvisor;

@Service
public class ChatService4 {

    private final ChatClient chatClient;

    private final LoggingAdvisor loggingAdvisor;
    private final TimingAdvisor timingAdvisor;
    private final SecurityAdvisor securityAdvisor;

    public ChatService4(ChatClient chatClient,
                       LoggingAdvisor loggingAdvisor,
                       TimingAdvisor timingAdvisor,
                       SecurityAdvisor securityAdvisor) {

        this.chatClient = chatClient;
        this.loggingAdvisor = loggingAdvisor;
        this.timingAdvisor = timingAdvisor;
        this.securityAdvisor = securityAdvisor;
    }

    public String ask(String question) {

        return chatClient.prompt()

                .advisors(
                        loggingAdvisor,
                        timingAdvisor,
                        securityAdvisor
                )

                .user(question)

                .call()

                .content();
    }
}