package com.taps.dronesapi.model;

import javax.persistence.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "medication")
public class Medication {
	
    //pattern to allowe only letters, numbers, ‘-‘, ‘_’
	String medication_name_pattern = "([a-zA-Z_0-9 -]+)";
	String medication_code_pattern = "([A-Z_0-9 ]+)";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "medication_name")
    private String medicationName;

    @Column(name = "weight")
    private double weight;

    @Column(name = "code")
    private String code;

    @Column(name = "image")
    private String image;

    public Medication() {

    }

    // initialize the variables
    public Medication(String medicationName, double weight, String code, String image) {
        this.medicationName = medicationName;
        this.weight = weight;
        this.code = code;
        this.image = image;
    }

    // id
    public long getId() {
        return id;
    }

    // Medication Name
    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
    	 if (medicationName.matches(medication_name_pattern)) {
    		 this.medicationName = medicationName;
    	 } else {
    		 System.out.println("allowed only letters, numbers, ‘-‘, ‘_’ on medication name");
    	 }
        
    }

    // Weight
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Code
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
    	 if (code.matches(medication_code_pattern)) {
    		 this.code = code;
    	 }else {
    		 System.out.println("allowed only upper case letters, underscore and numbers on medication code");
    	 }
       
    }

    // image
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Medication [id=" + id + ", medication_name=" + medicationName + ", weight=" + weight + ", code=" + code + ", image="
                + image + "]";
    }
}