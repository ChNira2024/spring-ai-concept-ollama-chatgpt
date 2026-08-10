package com.springai.service;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class StructuredOutputService {

    private final ChatClient chatClient;

    public StructuredOutputService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String generateEmployeeJson() {

        return chatClient.prompt()

                .user("""
                        Generate one employee.

                        Return ONLY valid JSON.

                        Do not return Markdown.
                        Do not use ```json.
                        Do not add explanations.

                        Format:

                        {
                          "name":"",
                          "age":0,
                          "designation":"",
                          "salary":0
                        }
                        """)

                .call()

                .content();
    }
    
    public String generateEmployeeJson2() {

        return chatClient.prompt()

                .system("""
                        You are an HR Assistant.

                        Always generate valid JSON.

                        Never generate Markdown.

                        Never generate explanations.

                        Never add extra fields.

                        """)
                .user("""
                        Generate one employee.

                        JSON fields:

                        name

                        age

                        designation

                        salary
                        """)

                .call()

                .content();

    }
    
    public String generateProductJson() {

        return chatClient.prompt()

                .system("Return only JSON.")

                .user("""
                        Generate one Product.

                        {
                          "name":"",
                          "price":0,
                          "category":""
                        }
                        """)

                .call()

                .content();

    }

}