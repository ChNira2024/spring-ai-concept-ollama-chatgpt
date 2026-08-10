package com.springai.service;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    //without advisor
    public String askWithOutAdVisors(String question){
    	return chatClient.prompt().user(question).call().content();
    }
    
    //with advisor
    public String askWithAdVisors(String question){
    	return chatClient.prompt().advisors(new SimpleLoggerAdvisor()).user(question).call().content();
    }

}