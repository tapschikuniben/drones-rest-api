package com.taps.dronesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.taps.dronesapi.model.Drone;

public interface DroneRepository extends JpaRepository<Drone, Long> {

    // check drones by current state
    List<Drone> findByState(String state);
    
    List<Drone> findBySerialNumber(String serialNumber);
    
    List<Drone> findByModelContaining(String model);
    
    @Transactional
    @Modifying
    @Query(value = "update Drone d set d.state = :state where d.serialNumber= :serial_number")
	void setUpdateState (@Param("state") String state, @Param("serial_number") String serial_number);
    
    
    @Transactional
    @Modifying
    @Query(value = "update Drone d set d.currentLoadID = :current_load_id where d.serialNumber= :serial_number")
	void setCurrentLoadID (@Param("current_load_id") long currentLoadID, @Param("serial_number") String serial_number);
    
}
