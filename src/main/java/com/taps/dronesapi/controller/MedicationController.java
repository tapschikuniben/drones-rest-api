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

import com.taps.dronesapi.model.Medication;
import com.taps.dronesapi.model.Medication;
import com.taps.dronesapi.repository.MedicationRepository;
import com.taps.dronesapi.repository.MedicationRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class MedicationController {
	
    @Autowired
    MedicationRepository medicationRepository;

    @GetMapping("/medication")
    public ResponseEntity<List<Medication>> getAllMedications(@RequestParam(required = false) String medicationName) {
        try {
            List<Medication> medication = new ArrayList<Medication>();

            if (medicationName == null)
            	medicationRepository.findAll().forEach(medication::add);
            else
            	medicationRepository.findByMedicationNameContaining(medicationName).forEach(medication::add);

            if (medication.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(medication, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/medication/{id}")
    public ResponseEntity<Medication> getMedicationById(@PathVariable("id") long id) {
        Optional<Medication> medicationData = medicationRepository.findById(id);

        if (medicationData.isPresent()) {
            return new ResponseEntity<>(medicationData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/medication")
    public ResponseEntity<Medication> createMedication(@RequestBody Medication medication) {
        try {
            Medication _medication = medicationRepository.save(new Medication(medication.getMedicationName(), medication.getWeight(),
                    medication.getCode(), medication.getImage()));
            return new ResponseEntity<>(_medication, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/medication/{id}")
    public ResponseEntity<Medication> updateMedication(@PathVariable("id") long id, @RequestBody Medication medication) {
        Optional<Medication> medicationData = medicationRepository.findById(id);

        if (medicationData.isPresent()) {
            Medication _medication = medicationData.get();
            _medication.setMedicationName(medication.getMedicationName());
            _medication.setWeight(medication.getWeight());
            _medication.setCode(medication.getCode());
            _medication.setImage(medication.getImage());
            return new ResponseEntity<>(medicationRepository.save(_medication), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/medication/{id}")
    public ResponseEntity<HttpStatus> deleteMedication(@PathVariable("id") long id) {
        try {
            medicationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}