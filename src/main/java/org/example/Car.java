package org.example;

class Car extends Vehicle {
    public Car(String id, String brand, String model, int year, double price) {
        super(id, brand, model, year, price);
    }

    @Override
    public String getType() {
        return "Car";
    }
}