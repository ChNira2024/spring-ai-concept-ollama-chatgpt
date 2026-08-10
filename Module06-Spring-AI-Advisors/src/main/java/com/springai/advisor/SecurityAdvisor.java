package com.springai.advisor;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.stereotype.Component;

@Component
public class SecurityAdvisor implements CallAdvisor {

    @Override
    public String getName() {
        return "SecurityAdvisor";
    }

    @Override
    public int getOrder() {
        return 3;
    }

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest request,
                                         CallAdvisorChain chain) {

        System.out.println("SecurityAdvisor BEFORE");

        ChatClientResponse response = chain.nextCall(request);

        System.out.println("SecurityAdvisor AFTER");

        return response;
    }
}