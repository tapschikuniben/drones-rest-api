package com.taps.dronesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taps.dronesapi.model.Drone;

public interface DroneRepository extends JpaRepository<Drone, Long> {

    // check drones by current state
    List<Drone> findByState(String state);

    List<Drone> findByModelContaining(String model);
}
