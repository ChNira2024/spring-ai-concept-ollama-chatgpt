package com.springai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springai.service.MultiModelService;


@RestController
public class MultiModelController {

    private final MultiModelService service;

    public MultiModelController(MultiModelService service) {
        this.service = service;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("provider") String provider,@RequestParam("question") String question) {
        return service.ask(provider, question);

    }

}

//GET: http://localhost:8080/chat?provider=ollama&question=Explain Spring Boot