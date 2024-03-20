package org.example;

abstract class Vehicle {
    protected String id;
    protected String brand;
    protected String model;
    protected int year;
    protected double price;
    protected boolean rented;

    public Vehicle(String id, String brand, String model, int year, double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.rented = false;
    }

    public abstract String getType();

    public String toCSV() {
        return id +";" + getType()+";" + brand + ";" + model + ";" + year + ";" + price + ";" + rented + ";";
    }

    @Override
    public String toString() {
        return brand + " " + model + " (" + year + ")";
    }

    public void rent() {
        rented = true;
    }

    public void returnVehicle() {
        rented = false;
    }

    public boolean isRented() {
        return rented;
    }

    public String getId() {
        return id;
    }
}