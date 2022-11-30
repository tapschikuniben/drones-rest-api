# drones-rest-api

---

# Task description

We have a fleet of **10 drones**. A drone is capable of carrying devices, other than cameras, and capable of delivering small loads. For our use case **the load is medications**.

---

A **Drone** has:
- serial number (100 characters max);
- model (Lightweight, Middleweight, Cruiserweight, Heavyweight);
- weight limit (500gr max);
- battery capacity (percentage);
- state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).

---

Each **Medication** has:
- name (allowed only letters, numbers, ‘-‘, ‘_’);
- weight;
- code (allowed only upper case letters, underscore and numbers);
- image (picture of the medication case).

---

# The service should allow:
- registering a drone;
- loading a drone with medication items;
- checking loaded medication items for a given drone;
- checking available drones for loading;
- check drone battery level for a given drone;

---

# Functional requirements
- Prevent the drone from being loaded with more weight that it can carry;
- Prevent the drone from being in LOADING state if the battery level is **below 25%**;
- Introduce a periodic task to check drones battery levels and create history/audit event log for this.


---
# Preloading data

- Drones will be pre-loaded on the initial build
- Medidation will be pre-loader on the initial build

---

# build/run/test instructions 

**Technology used** 

- Java 8
- Java IDE (Eclipse)
- MYSQL databse
- Postman(For testing) (I have included the expoterd file for postman for you to test the requets)

---

**building and runing the project**

- Unzip the project zip folder
- Import the unzipped folder into eclipse
- Update Project to update all the maven dependencies in Maven
- Maven Build the project and run using command **mvn spring-boot:run**

---

# Testing with postman

**Assumptions**

- Each drone has a unique serial number
- Each medication has a unique code
- A drone can carry one package of medication at a time , one medication package can no be assigned to different drones at the same time

---

**Registering a drone**

- POST method at localhost:8080/api/drones
![alt text](./assets/drone%20api%20images/register%20a%20drone.PNG)


---

- Expected Response
![alt text](./assets/drone%20api%20images/register%20a%20drone%20response.PNG)



---


**Update a drone**

- PUT method at localhost:8080/api/drones/{id}
![alt text](./assets/drone%20api%20images/update%20drone.PNG)


---

- Expected response
![alt text](./assets/drone%20api%20images/update%20drone%20response.PNG)



---

**Get all available drones for loading**
- GET method localhost:8080/api/drones/available


---


- Expected response
![alt text](./assets/drone%20api%20images/available%20drones%20for%20loading.PNG)



---

**Load a drone**

- POST localhost:8080/api/load_drone
![alt text](./assets/drone%20api%20images/loading%20drone%20with%20medication.PNG)



---


- GET loaded drone status should change to LOADED, The currentLoadID will hold the ID of the load (to identify the loaded medication)

- Check the state of the updated drone at localhost:8080/api/drones/serial/{SerialNumber} 
![alt text](./assets/drone%20api%20images/loaded%20drone.PNG)


---


**checking loaded medication items for a given drone;**

- GET loaded medication on a drone localhost:8080/api/drones/loaded-items/{SerialNumber}

![alt text](./assets/drone%20api%20images/drone%20loaded%20medication.PNG)


---


**check drone battery level for a given drone;**

- GET battery level of a drone localhost:8080/api/drones/get-battery-level/{Serial Number}

![alt text](./assets/drone%20api%20images/drone%20battery%20level.PNG)



---


**Event log for battery level for all drones**

![alt text](./assets/drone%20api%20images/event%20battery%20log%20for%20all%20drones.PNG)