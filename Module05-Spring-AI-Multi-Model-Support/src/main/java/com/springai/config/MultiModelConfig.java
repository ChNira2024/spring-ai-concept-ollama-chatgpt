package com.springai.config;


import java.util.HashMap;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultiModelConfig {

    @Bean
    public Map<String, ChatClient> chatClients(
            OllamaChatModel ollamaChatModel,
            GoogleGenAiChatModel googleGenAiChatModel) {

        Map<String, ChatClient> map = new HashMap<>();

        map.put("ollama",ChatClient.builder(ollamaChatModel).build());

        map.put("gemini",ChatClient.builder(googleGenAiChatModel).build());

        return map;
    }

}