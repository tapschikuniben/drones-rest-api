package com.taps.dronesapi.model;

import javax.persistence.*;


@Entity
@Table(name = "drones")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "model")
    private String model;

    @Column(name = "weight_limit")
    private double weightLimit;

    @Column(name = "battery_capacity")
    private int batteryCapacity;

    @Column(name = "state")
    private String state;
    
    @Column(name = "current_load_id")
    private long currentLoadID;

    public Drone() {

    }

    // initialize the variables
    public Drone(String serialNumber, String model, double weightLimit, int batteryCapacity, String state,long currentLoadID) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
        this.currentLoadID = currentLoadID;
    }

    // id
    public long getId() {
        return id;
    }

    // Serial Number
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
    	if(serialNumber.length() > 100) {
    		System.out.println("Serial Number can not be more than 100 characters");
    		return;
    	}
        this.serialNumber = serialNumber;
    }

    // Model
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    // weightLimit
    public double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(double weightLimit) {
        this.weightLimit = weightLimit;
    }

    // batteryCapacity
    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    // state
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    // load ID
    public long getCurrentLoadID() {
        return currentLoadID;
    }

    public void setCurrentLoadID(long currentLoadID) {
        this.currentLoadID = currentLoadID;
    }

    @Override
    public String toString() {
        return "Drone [id=" + id + ", serial_number=" + serialNumber + ", model=" + model + ", weight_limit="
                + weightLimit + ", battery_capacity=" + batteryCapacity + ", state="
                + state + ", current_load_id=" + currentLoadID + "]";
    }
}