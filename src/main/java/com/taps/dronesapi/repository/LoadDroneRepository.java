package com.taps.dronesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taps.dronesapi.model.LoadDrone;

public interface LoadDroneRepository extends JpaRepository<LoadDrone, Long> {
	
	List<LoadDrone> findByDroneSerialNumberContaining(String droneSerialNumber);
	
}