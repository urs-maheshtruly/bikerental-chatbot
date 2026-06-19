package com.example.chatBot.repository;

import com.example.chatBot.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {

    // Get only available bikes
    List<Bike> findByAvailableTrue();

    // Get bikes by location keyword (e.g. "Hyderabad")
    List<Bike> findByLocationContainingIgnoreCase(String location);
}