package com.springai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springai.service.MultiModelService2;


@RestController
public class MultiModelController2 {

	 private final MultiModelService2 service;

	    public MultiModelController2(MultiModelService2 service) {
	        this.service = service;
	    }
	
	    //OpenAI API
	    @GetMapping("/geminiai")
	    public ResponseEntity<String> chatGeminiAI(@RequestParam("q") String q) {
	        return ResponseEntity.ok(service.askGeminiAI(q));
	    }

	    // Ollama API
	    @GetMapping("/ollama")
	    public ResponseEntity<String> chatOllama(@RequestParam("q") String q) {
	        return ResponseEntity.ok(service.askOllama(q));
	    }

	    // Smart API (BEST)
	    @GetMapping("/smart")
	    public ResponseEntity<String> chatSmart(@RequestParam("q") String q) {
	        return ResponseEntity.ok(service.askSmart(q));
	    }

}

//GET: http://localhost:8080/chat?provider=ollama&question=Explain Spring Boot