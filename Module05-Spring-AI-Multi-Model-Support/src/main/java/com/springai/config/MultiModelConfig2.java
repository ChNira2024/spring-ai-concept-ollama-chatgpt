package com.springai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultiModelConfig2 {
	@Bean("openAIChatClient")
	public ChatClient openAIChatModel(GoogleGenAiChatModel googleGenAiChatModel) {
		
		return ChatClient.builder(googleGenAiChatModel).build();
		
	}
	
	@Bean("ollamaChatClient")
	public ChatClient ollamaChatModel(OllamaChatModel ollamaChatModel) {
		
		return ChatClient.builder(ollamaChatModel).build();
		
	}
}