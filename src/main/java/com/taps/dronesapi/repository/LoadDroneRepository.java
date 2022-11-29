package com.taps.dronesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.taps.dronesapi.model.LoadDrone;

public interface LoadDroneRepository extends JpaRepository<LoadDrone, Long> {
	
	List<LoadDrone> findByDroneSerialNumberContaining(String droneSerialNumber);
	
	@Query("SELECT load FROM LoadDrone load WHERE load.id= :id")
	List<LoadDrone> findDroneLoadByID(@Param("id") long id);
	
}