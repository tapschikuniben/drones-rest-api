package com.taps.dronesapi.periodic;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.taps.dronesapi.model.Drone;
import com.taps.dronesapi.repository.DroneRepository;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class BatteryLog {
	
	@Autowired
	private DroneRepository droneRepository;
	
	@Scheduled(fixedRate = 5000)
	public void periodicChecksOnBAtteryLevels() {
		
		Logger logger = LoggerFactory.getLogger(BatteryLog.class);
		
		List<Drone> drones = droneRepository.findAll();
		
		for(int i=0; i<drones.size(); i++) {
	            
			logger.info("Batery level--: SerialNumber  "+ drones.get(i).getSerialNumber()+"  Battery Level "+ drones.get(i).getBatteryCapacity());
			
	        }
		
	}
	
}