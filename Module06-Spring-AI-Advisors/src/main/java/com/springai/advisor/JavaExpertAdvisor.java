package com.springai.advisor;


import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.stereotype.Component;

@Component
public class JavaExpertAdvisor implements CallAdvisor {

    @Override
    public String getName() {
        return "JavaExpertAdvisor";
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest request,CallAdvisorChain chain) {

        System.out.println("========= BEFORE MODEL =========");
        System.out.println(request); //We are intercepting the request.

        // Continue to next advisor/model
        ChatClientResponse response = chain.nextCall(request); //We are intercepting the request. At the moment we are only logging it.

        System.out.println("========= AFTER MODEL =========");
        System.out.println(response.chatResponse().getResult().getOutput().getText());

        return response;
    }


}