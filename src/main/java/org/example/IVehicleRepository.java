package org.example;

import java.util.List;

interface IVehicleRepository {
    void rentCar(String id);
    void returnCar(String id);
    List<Vehicle> getVehicles();
    void saveVehicles();
    void addVehicle(Vehicle vehicle);
    void removeVehicle(String vehicleId);

}