package com.springai.advisor;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.stereotype.Component;

@Component
public class TimingAdvisor implements CallAdvisor {

    @Override
    public String getName() {
        return "TimingAdvisor";
    }

    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest request,
                                         CallAdvisorChain chain) {

        long start = System.currentTimeMillis();

        System.out.println("TimingAdvisor BEFORE");

        ChatClientResponse response = chain.nextCall(request);

        long end = System.currentTimeMillis();

        System.out.println("Execution Time : " + (end - start));

        System.out.println("TimingAdvisor AFTER");

        return response;
    }
}