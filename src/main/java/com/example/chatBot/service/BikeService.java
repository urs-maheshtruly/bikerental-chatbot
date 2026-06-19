package com.example.chatBot.service;

import com.example.chatBot.model.Bike;
import com.example.chatBot.repository.BikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    private final BikeRepository bikeRepository;

    // Spring automatically injects BikeRepository here
    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    // Get all bikes
    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }

    // Get only available bikes
    public List<Bike> getAvailableBikes() {
        return bikeRepository.findByAvailableTrue();
    }

    // Search bikes by city
    public List<Bike> getBikesByLocation(String location) {
        return bikeRepository.findByLocationContainingIgnoreCase(location);
    }
}