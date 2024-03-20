package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class VehicleRepository implements IVehicleRepository {
    private List<Vehicle> vehicles;
    private String filePath;

    public VehicleRepository(String filePath) {
        this.filePath = filePath;
        this.vehicles = new ArrayList<>();
        loadVehicles();
    }

    private void loadVehicles() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 6) {
                    String id = parts[0];
                    String type = parts[1];
                    String brand = parts[2];
                    String model = parts[3];
                    int year = Integer.parseInt(parts[4]);
                    double price = Double.parseDouble(parts[5]);
                    boolean rented = Boolean.parseBoolean(parts[6]);
                    Vehicle vehicle;
                    if (type.equals("Car")) {
                        vehicle = new Car(id, brand, model, year, price);
                    } else {
                        String category = parts[7];
                        vehicle = new Motorcycle(id, brand, model, year, price, category);
                    }
                    vehicle.rented = rented;
                    vehicles.add(vehicle);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rentCar(String id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equals(id) && !vehicle.isRented()) {
                vehicle.rent();
                saveVehicles();
                System.out.println("Vehicle rented: " + vehicle);

                return;
            }
        }
        System.out.println("Vehicle not available for rent or does not exist.");
    }

    @Override
    public void returnCar(String id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equals(id) && vehicle.isRented()) {
                vehicle.returnVehicle();
                saveVehicles();
                System.out.println("Vehicle returned: " + vehicle);
                return;
            }
        }
        System.out.println("Vehicle not found or not rented by you.");
    }

    @Override
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    @Override
    public void saveVehicles() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Vehicle vehicle : vehicles) {
                bw.write(vehicle.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        saveVehicles();
    }
    @Override
    public void removeVehicle(String vehicleId) {
        vehicles.removeIf(v -> v.getId().equals(vehicleId));
        saveVehicles();
    }

}