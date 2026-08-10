package com.springai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springai.service.AIService;

@RestController
@RequestMapping("/ai")
public class AIController {

    private final AIService service;

    public AIController(AIService service) {
        this.service = service;
    }

    @GetMapping("/ask3")
    public String ask3() {
        return service.ask3();
    }
    
    @GetMapping("/template")
    public String template(@RequestParam String topic){
        return service.promptTemplate(topic);

    }
    @GetMapping("/user")
    public String user(@RequestParam String question){
        return service.userMessage(question);

    }
    
    @GetMapping("/system")
    public String system(@RequestParam String question){
        return service.systemMessage(question);
    }
    
    
}