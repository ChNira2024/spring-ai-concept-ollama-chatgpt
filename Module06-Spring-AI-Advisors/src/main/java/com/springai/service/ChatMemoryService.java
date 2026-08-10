package com.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
public class ChatMemoryService {

	private final ChatClient chatClient;

	private final ChatMemory chatMemory;

	public ChatMemoryService(ChatClient chatClient, ChatMemory chatMemory) {

		this.chatClient = chatClient;
		this.chatMemory = chatMemory;

	}

	//InMemoryChatMemory
	public String ask(String conversationId, String question) {

		return chatClient
				.prompt()
				.advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID, conversationId))
				.user(question)
				.call()
				.content();
	}
}