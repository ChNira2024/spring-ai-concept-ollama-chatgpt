package com.springai.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springai.service.ChatMemoryService;

@RestController
public class ChatMemoryController {

    private final ChatMemoryService service;

    public ChatMemoryController(ChatMemoryService service) {
        this.service = service;
    }

    //InMemoryChatMemory
    @GetMapping("/chat")
    public String chat(@RequestParam String conversationId,@RequestParam String question) {
        return service.ask(conversationId, question);

    }

}

//GET http://localhost:8080/chat?conversationId=101&question=My name is Niranjana