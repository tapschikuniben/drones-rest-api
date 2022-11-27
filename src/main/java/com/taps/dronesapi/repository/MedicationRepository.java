package com.taps.dronesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taps.dronesapi.model.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
	
    List<Medication> findByMedicationNameContaining(String medicationName);
}
