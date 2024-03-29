package com.taps.dronesapi.model;

import javax.persistence.*;

@Entity
@Table(name = "load_drones")
public class LoadDrone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "drone_serial_number")
    private String droneSerialNumber;

    @Column(name = "medication_code")
    private String medicationCode;

    @Column(name = "medication_weight")
    private double medicationWeight;
    
    @Column(name = "source")
    private String source;

    @Column(name = "destination")
    private String destination;

    public LoadDrone() {

    }

    // initialize the variables
    public LoadDrone(String droneSerialNumber, String medicationCode, double medicationWeight,String source, String destination) {
        this.droneSerialNumber = droneSerialNumber;
        this.medicationCode = medicationCode;
        this.medicationWeight = medicationWeight;
        this.source = source;
        this.destination = destination;
    }

    // id
    public long getId() {
        return id;
    }

    // Serial Number
    public String getDroneSerialNumber() {
        return droneSerialNumber;
    }

    public void setDroneSerialNumber(String droneSerialNumber) {
        this.droneSerialNumber = droneSerialNumber;
    }

 
    //Medication Code
    public String getMedicationCode() {
        return medicationCode;
    }

    public void setMedicationCode(String medicationCode) {
        this.medicationCode = medicationCode;
    }
    
    //Medication Weight
    public double getMedicationWeight() {
        return medicationWeight;
    }

    public void setMedicationWeight(double medicationWeight) {
        this.medicationWeight = medicationWeight;
    }
    
    
    //Source
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    
    
    //destination
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    


    @Override
    public String toString() {
        return "LoadDrone [id=" + id + ", drone_serial_number=" + droneSerialNumber + ", medication_code=" + medicationCode + " , medication_weight=" + medicationWeight + ", source="
                + source + ", destination=" + destination + "]";
    }
}