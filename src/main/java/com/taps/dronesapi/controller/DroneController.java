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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taps.dronesapi.model.Drone;
import com.taps.dronesapi.repository.DroneRepository;;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class DroneController {

    @Autowired
    DroneRepository droneRepository;

    @GetMapping("/drones")
    public ResponseEntity<List<Drone>> getAllDrones(@RequestParam(required = false) String model) {
        try {
            List<Drone> drones = new ArrayList<Drone>();

            if (model == null)
                droneRepository.findAll().forEach(drones::add);
            else
                droneRepository.findByModelContaining(model).forEach(drones::add);

            if (drones.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(drones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/drones/{id}")
    public ResponseEntity<Drone> getDroneById(@PathVariable("id") long id) {
        Optional<Drone> droneData = droneRepository.findById(id);

        if (droneData.isPresent()) {
            return new ResponseEntity<>(droneData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/drones")
    public ResponseEntity<Drone> createDrone(@RequestBody Drone drone) {
        try {
            Drone _drone = droneRepository.save(new Drone(drone.getSerialNumber(), drone.getModel(),
                    drone.getWeightLimit(), drone.geBatteryCapacity(), drone.getState()));
            return new ResponseEntity<>(_drone, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/drones/{id}")
    public ResponseEntity<Drone> updateDrone(@PathVariable("id") long id, @RequestBody Drone drone) {
        Optional<Drone> droneData = droneRepository.findById(id);

        if (droneData.isPresent()) {
            Drone _drone = droneData.get();
            _drone.setSerialNumber(drone.getSerialNumber());
            _drone.setModel(drone.getModel());
            _drone.setWeightLimit(drone.getWeightLimit());
            _drone.setBatteryCapacity(drone.geBatteryCapacity());
            _drone.setState(drone.getState());
            return new ResponseEntity<>(droneRepository.save(_drone), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/drones/{id}")
    public ResponseEntity<HttpStatus> deleteDrone(@PathVariable("id") long id) {
        try {
            droneRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/drones/available")
    public ResponseEntity<List<Drone>> findByState() {
        try {
            List<Drone> drones = droneRepository.findByState("IDLE");

            if (drones.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(drones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
