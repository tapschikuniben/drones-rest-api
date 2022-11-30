package com.taps.dronesapi.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.taps.dronesapi.model.Drone;
import com.taps.dronesapi.model.Medication;

import com.taps.dronesapi.repository.DroneRepository;
import com.taps.dronesapi.repository.MedicationRepository;



@SpringBootApplication
public class PreloadData {
	
	 @Autowired
	 DroneRepository droneRepository;
	 
	 @Autowired
	 MedicationRepository medicationRepository;
	 
	 @PostConstruct
	 public void saveDrone() {
		 
        //first check if drone collections exists
		 List<Drone> all_drones = droneRepository.findAll();
		 
		 if(all_drones.isEmpty()) {
			 Drone drone_1 = new Drone("D001", "Lightweight", 500.00, 80, "IDLE", 0);
			 Drone drone_2 = new Drone("D002", "Middleweight", 500.00, 50, "IDLE", 0);
			 Drone drone_3 = new Drone("D003", "Cruiserweight", 500.00, 80, "IDLE", 0);
			 Drone drone_4 = new Drone("D004", "Heavyweight", 500.00, 70, "IDLE", 0);
			 Drone drone_5 = new Drone("D005", "Heavyweight", 500.00, 60, "IDLE", 0);
			 Drone drone_6 = new Drone("D006", "Heavyweight", 500.00, 20, "IDLE", 0);
			 Drone drone_7 = new Drone("D007", "Cruiserweight", 500.00, 30, "IDLE", 0);
			 Drone drone_8 = new Drone("D008", "Lightweight", 500.00, 10, "IDLE", 0);
			 Drone drone_9 = new Drone("D009", "Cruiserweight", 500.00, 50, "IDLE", 0);
			 Drone drone_10 = new Drone("D0010", "Middleweight", 500.00, 100, "IDLE", 0);
			 
			 droneRepository.save(drone_1); 
			 droneRepository.save(drone_2); 
			 droneRepository.save(drone_3); 
			 droneRepository.save(drone_4); 
			 droneRepository.save(drone_5);
			 droneRepository.save(drone_6); 
			 droneRepository.save(drone_7); 
			 droneRepository.save(drone_8); 
			 droneRepository.save(drone_9); 
			 droneRepository.save(drone_10); 
		 }
		 
		 
		//first check if drone collections exists
		 List<Medication> all_medications = medicationRepository.findAll();
		 
		 if(all_medications.isEmpty()) {
			 Medication medication_1 = new Medication("Acetaminophen", 200.00, "M_001", "image");
			 Medication medication_2 = new Medication("Acetaminophen", 300.00, "M_002", "image");
			 Medication medication_3 = new Medication("Acetaminophen", 400.00, "M_003", "image");
			 Medication medication_4 = new Medication("Acetaminophen", 200.00, "M_004", "image");
			 Medication medication_5 = new Medication("Acetaminophen", 300.00, "M_005", "image");
			 Medication medication_6 = new Medication("Acetaminophen", 300.00, "M_006", "image");
			 Medication medication_7 = new Medication("Acetaminophen", 450.00, "M_007", "image");
			 Medication medication_8 = new Medication("Acetaminophen", 500.00, "M_008", "image");
			 Medication medication_9 = new Medication("Acetaminophen", 200.00, "M_009", "image");
			 Medication medication_10 = new Medication("Acetaminophen", 150.00, "M_0010", "image");
			 
			 medicationRepository.save(medication_1); 
			 medicationRepository.save(medication_2); 
			 medicationRepository.save(medication_3); 
			 medicationRepository.save(medication_4); 
			 medicationRepository.save(medication_5);
			 medicationRepository.save(medication_6); 
			 medicationRepository.save(medication_7); 
			 medicationRepository.save(medication_8); 
			 medicationRepository.save(medication_9); 
			 medicationRepository.save(medication_10); 
		 }
	
	 }
     
}