package com.springai.controller;


import com.springai.service.ChatService;
import com.springai.service.ChatService2;
import com.springai.service.ChatService3;
import com.springai.service.ChatService4;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;
    private final ChatService2 chatService2;
    private final ChatService3 chatService3;
    private final ChatService4 chatService4;

    public ChatController(ChatService chatService , ChatService2 chatService2, ChatService3 chatService3,ChatService4 chatService4) {
        this.chatService = chatService;
        this.chatService2 = chatService2;
        this.chatService3 = chatService3;
        this.chatService4 = chatService4;
    }

    //without Advisor
    @GetMapping
    public String askWithOutAdVisors(@RequestParam("question") String question){
        return chatService.askWithOutAdVisors(question);

    }
    
  //with Advisor
    @GetMapping
    public String askWithAdVisors(@RequestParam("question") String question){
        return chatService.askWithAdVisors(question);

    }
    
    //custom advisors
    @GetMapping
    public String chat(@RequestParam String question) {
        return chatService2.ask(question);

    }

    //Logging advisors
    @GetMapping
    public String chatLoggingAdvisor(@RequestParam String question) {
        return chatService3.ask(question);

    }
    
  //Advisor Chain
    @GetMapping
    public String chatAdvisorChain(@RequestParam String question) {
        return chatService4.ask(question);

    }
    
    

}

//GET http://localhost:8080/chat?question=Explain Spring Boot