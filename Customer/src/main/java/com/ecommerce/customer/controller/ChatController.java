package com.ecommerce.customer.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.ContentType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ChatController {

    private static final String OPENAI_API_KEY = "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";
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    @PostMapping("/chat")
    public String chat(@RequestBody String userMessage) {
        System.out.println("Received user message: " + userMessage);

        // Call OpenAI GPT API to generate a response based on userMessage
        String response = generateResponse(userMessage);
        System.out.println("Generated response: " + response);

        // Return the response to the client
        return response;
    }

    private String generateResponse(String userMessage) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(OPENAI_API_URL);
            int maxTokens = 500;

            // Set headers
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Authorization", "Bearer " + OPENAI_API_KEY);

            // Use Jackson to construct the JSON payload
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> requestBodyMap = new HashMap<>();
            List<Map<String, String>> messages = new ArrayList<>();
            Map<String, String> userMessageMap = new HashMap<>();
            userMessageMap.put("role", "user");
            userMessageMap.put("content", userMessage);
            messages.add(userMessageMap);
            requestBodyMap.put("messages", messages);
            requestBodyMap.put("model", "gpt-3.5-turbo");
            requestBodyMap.put("max_tokens", maxTokens);
            requestBodyMap.put("temperature", 0.7);

            // Convert the map to JSON
            String requestBody = objectMapper.writeValueAsString(requestBodyMap);

            // Set request body
            httpPost.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));

            // Execute the request
            HttpResponse httpResponse = httpClient.execute(httpPost);

            // Parse and return the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating response";
        }
    }
}
