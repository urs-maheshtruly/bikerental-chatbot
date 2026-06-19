package com.example.chatBot.controller;

import com.example.chatBot.model.Bike;
import com.example.chatBot.service.BikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bikes")
public class BikeController {

    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    // GET http://localhost:8080/api/bikes
    @GetMapping
    public List<Bike> getAllBikes() {
        return bikeService.getAllBikes();
    }

    // GET http://localhost:8080/api/bikes/available
    @GetMapping("/available")
    public List<Bike> getAvailableBikes() {
        return bikeService.getAvailableBikes();
    }

    // GET http://localhost:8080/api/bikes/search?location=Hyderabad
    @GetMapping("/search")
    public List<Bike> searchByLocation(@RequestParam String location) {
        return bikeService.getBikesByLocation(location);
    }
}