package com.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MultiModelService2 {

    private final ChatClient googleGeminiChatClient;
    private final ChatClient ollamaChatClient;

    public MultiModelService2(@Qualifier("googleGeminiChatClient") ChatClient googleGeminiChatClient,
    		                 @Qualifier("ollamaChatClient") ChatClient ollamaChatClient) 
    {

        this.googleGeminiChatClient = googleGeminiChatClient;
        this.ollamaChatClient = ollamaChatClient;
    }

    //OpenAI call
    public String askGeminiAI(String prompt){
        return googleGeminiChatClient.prompt(prompt).call().content();
    }

    //Ollama call
    public String askOllama(String prompt) {
        return ollamaChatClient.prompt(prompt).call().content();
    }

    //Smart routing
    public String askSmart(String prompt) {

        String lower = prompt.toLowerCase();

        if (lower.contains("real-time") || lower.contains("code") || lower.contains("example")) 
        {

            return askOllama(prompt); //if prompt is for coding related then choose → ollama
        }

        return askGeminiAI(prompt); // if prompt is only text related then choose → openai
    }
}