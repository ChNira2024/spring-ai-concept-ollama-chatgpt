package com.springai.advisor;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.stereotype.Component;

@Component
public class LoggingAdvisor implements CallAdvisor {

    @Override
    public String getName() {
        return "LoggingAdvisor";
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest request,
                                         CallAdvisorChain chain) {

        long start = System.currentTimeMillis();

        System.out.println("\n========== AI REQUEST ==========");
        System.out.println(request);

        ChatClientResponse response = chain.nextCall(request);

        long end = System.currentTimeMillis();

        System.out.println("\n========== AI RESPONSE ==========");
        System.out.println(
                response.chatResponse()
                        .getResult()
                        .getOutput()
                        .getText());

        System.out.println("\nResponse Time : " + (end - start) + " ms");

        return response;
    }
}