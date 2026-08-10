package com.springai.service;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final ChatModel chatModel;
    private final ChatClient chatClient;

    public AIService(ChatModel chatModel,ChatClient chatClient) {
        this.chatModel = chatModel;
        this.chatClient = chatClient;
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
    
   
}
