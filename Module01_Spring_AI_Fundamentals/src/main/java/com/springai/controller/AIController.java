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

    @GetMapping("/ask")
    public String ask(@RequestParam("message") String message) {

        return service.askAI(message);

    }

    @GetMapping("/teacher")
    public String teacher(@RequestParam("question") String question){

        return service.javaTeacher(question);

    }
    
    @GetMapping("/conversation")
    public String conversation(){

        return service.conversation();

    }
    
    
    
}