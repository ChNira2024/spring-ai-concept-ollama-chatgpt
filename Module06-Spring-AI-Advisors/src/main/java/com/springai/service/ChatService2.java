package com.springai.service;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.springai.advisor.JavaExpertAdvisor;

@Service
public class ChatService2 {

	private final ChatClient chatClient;
    private final JavaExpertAdvisor advisor;

    public ChatService2(ChatClient chatClient,JavaExpertAdvisor advisor) {
        this.chatClient = chatClient;
        this.advisor = advisor;
    }

    //custom advisors
    public String ask(String question) {
        return chatClient.prompt().advisors(advisor).user(question).call().content();
    }
    
    

}