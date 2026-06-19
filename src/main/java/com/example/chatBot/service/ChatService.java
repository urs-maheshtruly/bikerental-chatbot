package com.example.chatBot.service;

import com.example.chatBot.repository.BikeRepository;
import com.example.chatBot.model.Bike;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final ChatClient chatClient;
    private final BikeRepository bikeRepository;

    public ChatService(ChatClient.Builder builder, BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
        this.chatClient = builder
                .defaultSystem("""
                        You are a helpful assistant for a Bike Rental service.
                        Help customers with bike availability, pricing and bookings.
                        Pricing: Mountain Bike = 50/hour, City Bike = 40/hour, Electric Bike = 80/hour.
                        Be friendly and keep answers short.
                        """)
                .build();
    }

    public String chat(String userMessage) {

        // Step 1: fetch available bikes from database
        List<Bike> availableBikes = bikeRepository.findByAvailableTrue();

        // Step 2: convert bike list to readable text for Gemini
        StringBuilder bikeContext = new StringBuilder("Currently available bikes:\n");
        for (Bike bike : availableBikes) {
            bikeContext.append("- ")
                    .append(bike.getType())
                    .append(" at ")
                    .append(bike.getLocation())
                    .append(" for Rs.")
                    .append(bike.getPricePerHour())
                    .append("/hour\n");
        }

        // Step 3: send bike data + user message to groq
        return chatClient.prompt()
                .user(bikeContext + "\nCustomer question: " + userMessage)
                .call()
                .content();
    }
}