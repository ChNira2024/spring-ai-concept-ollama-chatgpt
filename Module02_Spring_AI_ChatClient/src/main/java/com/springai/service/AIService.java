package com.springai.service;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class AIService {

    private final ChatModel chatModel;
    private final ChatClient chatClient;

    public AIService(ChatModel chatModel,ChatClient chatClient) {
        this.chatModel = chatModel;
        this.chatClient = chatClient;
    }

    //ChatModel
    public String ask(String question) {
        return chatModel.call(question);
    }
    
    //ChatModel + Prompt
    public String ask2() {
        Prompt prompt = new Prompt("Explain Spring Boot");
        return chatModel.call(prompt).getResult().getOutput().getText();//If you're using ChatClient, you normally don't create a Prompt object yourself for simple cases.
    }
    
    //ChatModel + SystemMessage + UserMessage
    public String ask3() {
        Prompt prompt = new Prompt(

                new SystemMessage("""
                        You are a Java Trainer.
                        """),

                new UserMessage("""
                        Explain Dependency Injection.
                        """)
        );
        return chatModel.call(prompt)
                        .getResult()
                        .getOutput()
                        .getText();
    }
   
    //ChatModel + Prompt + ChatResponse
    public String ask4(String question) {
        Prompt prompt = new Prompt(question);
        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().getText();
    }
    
    //ChatClient + OllamaOptions + Temperature + maxTokens
    public String ask5(String question) {

    	return chatClient.prompt()
    	        .user(question)
    	        .options(
    	                OllamaChatOptions.builder() //In Spring AI 2.0.0, the options() method expects the Builder, not the built object.
    	                        .model("llama3.2:3b")
    	                        .temperature(0.7)
    	                        .maxTokens(200)
    	                        //.build()
    	        )
    	        .call()
    	        .content();
    }
    
    //ChatModel + PromptTemplate + ChatResponse
    public String promptTemplate(String topic) {

        PromptTemplate template =
                new PromptTemplate("""
                Explain {topic}
                with real-world examples.
                """);

        Prompt prompt =
                template.create(
                        Map.of("topic", topic)
                );
        
        //return chatModel.call(prompt).getResult().getOutput().getText();  also work

        ChatResponse response =  chatModel.call(prompt);
        return response.getResult().getOutput().getText();
    }
    
    //ChatModel + Prompt + UserMessage
    public String userMessage(String question){

        Prompt prompt =
                new Prompt(
                        new UserMessage(question)
                );

        return chatModel.call(prompt)
                .getResult()
                .getOutput()
                .getText();

    }
    //ChatModel + Prompt + SystemMessage
    public String systemMessage(String question){

        Prompt prompt =
                new Prompt(
                        List.of(

                                new SystemMessage(
                                        "You are a Senior Java Trainer. Answer only about Java."
                                ),

                                new UserMessage(question)

                        )
                );

        return chatModel.call(prompt)
                .getResult()
                .getOutput()
                .getText();

    }
    
    //ChatModel + Prompt + AssistantMessage
    public String assistantMessage(){

        Prompt prompt =
                new Prompt(

                        List.of(

                                new UserMessage(
                                        "What is Spring Boot?"
                                ),

                                new AssistantMessage(
                                        "Spring Boot is a framework that simplifies Spring development."
                                ),

                                new UserMessage(
                                        "What are its advantages?"
                                )

                        )

                );

        return chatModel.call(prompt)
                .getResult()
                .getOutput()
                .getText();

    }
    //Streaming
    public Flux<String> stream(String question){

        return chatClient.prompt()
                .user(question)
                .stream()
                .content();

    }
    //ChatResponse
    public ChatResponse response(String question){

        Prompt prompt =
                new Prompt(question);

        return chatModel.call(prompt);

    }
}
