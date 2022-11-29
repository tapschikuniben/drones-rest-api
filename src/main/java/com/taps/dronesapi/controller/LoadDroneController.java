package com.taps.dronesapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taps.dronesapi.model.LoadDrone;
import com.taps.dronesapi.repository.DroneRepository;
import com.taps.dronesapi.repository.LoadDroneRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class LoadDroneController {
	
	@Autowired
    LoadDroneRepository loadDroneRepository;
	
	@Autowired
    DroneRepository droneRepository;
	
	
   @GetMapping("/load_drone")
    public ResponseEntity<List<LoadDrone>> getAllLoadDrones(@RequestParam(required = false) String droneSerialNumber) {
        try {
            List<LoadDrone> loadDrone = new ArrayList<LoadDrone>();

            if (droneSerialNumber == null)
                loadDroneRepository.findAll().forEach(loadDrone::add);
            else
                loadDroneRepository.findByDroneSerialNumberContaining(droneSerialNumber).forEach(loadDrone::add);

            if (loadDrone.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(loadDrone, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
    @PostMapping("/load_drone")
    public ResponseEntity<LoadDrone> createLoadDrone(@RequestBody LoadDrone loadDrone) {
        try {
            LoadDrone _loadDrone = loadDroneRepository.save(new LoadDrone(loadDrone.getDroneSerialNumber(), loadDrone.getMedicationCode(),
                    loadDrone.getSource(), loadDrone.getDestination()));
            
            // Update drone status to loaded
        	droneRepository.setUpdateState("LOADED", loadDrone.getDroneSerialNumber());
        	
            //Update drone current load id
        	droneRepository.setCurrentLoadID(_loadDrone.getId(), loadDrone.getDroneSerialNumber());
            
            return new ResponseEntity<>(_loadDrone, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/load_drone/{id}")
    public ResponseEntity<LoadDrone> getLoadDroneById(@PathVariable("id") long id) {
        Optional<LoadDrone> loadDroneData = loadDroneRepository.findById(id);

        if (loadDroneData.isPresent()) {
            return new ResponseEntity<>(loadDroneData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/load_drone/{id}")
        public ResponseEntity<LoadDrone> updateLoadDrone(@PathVariable("id") long id, @RequestBody LoadDrone loadDrone) {
            Optional<LoadDrone> loadDroneData = loadDroneRepository.findById(id);

            if (loadDroneData.isPresent()) {
                LoadDrone _loadDrone = loadDroneData.get();
                _loadDrone.setDroneSerialNumber(loadDrone.getDroneSerialNumber());
                _loadDrone.setMedicationCode(loadDrone.getMedicationCode());
                _loadDrone.setSource(loadDrone.getSource());
                _loadDrone.setDestination(loadDrone.getDestination());
                return new ResponseEntity<>(loadDroneRepository.save(_loadDrone), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/load_drone/{id}")
        public ResponseEntity<HttpStatus> deleteLoadDrone(@PathVariable("id") long id) {
            try {
                loadDroneRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

	
}