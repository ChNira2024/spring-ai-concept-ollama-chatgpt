package com.springai.controller;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springai.service.AIService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class AIController {

    private final AIService service;

    public AIController(AIService service) {
        this.service = service;
    }

    @GetMapping("/ask")
    public String ask(@RequestParam("message") String message) {
        return service.ask(message);
    }
    

    @GetMapping("/ask2")
    public String ask2() {
        return service.ask2();
    }
    

    @GetMapping("/ask3")
    public String ask3() {
        return service.ask3();
    }
    
    @GetMapping("/ask4")
    public String ask4(@RequestParam("question") String question) {
        return service.ask4(question);
    }
    
    @GetMapping("/ask5")
    public String ask5(@RequestParam("question") String question) {

        return service.ask5(question);
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
    
    @GetMapping("/assistant")
    public String assistant(){
    	return service.assistantMessage();
    }
    
    @GetMapping(value="/stream",produces = MediaType.TEXT_PLAIN_VALUE)
    public Flux<String> stream(@RequestParam String question){
    		    return service.stream(question);
    }
    
    @GetMapping("/response")
    public ChatResponse response(@RequestParam String question){
        return service.response(question);
    }
}