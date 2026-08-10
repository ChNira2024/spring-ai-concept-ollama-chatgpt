package com.springai.service;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class MultiModelService {

    private final Map<String, ChatClient> chatClients;

    public MultiModelService(Map<String, ChatClient> chatClients) {
        this.chatClients = chatClients;
    }

    public String ask(String provider, String question) {

        ChatClient client = chatClients.get(provider.toLowerCase());

        if (client == null) {
            throw new RuntimeException("Provider Not Found");
        }

        return client.prompt()
                .user(question)
                .call()
                .content();

    }

}