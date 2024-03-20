package org.example;

class Motorcycle extends Vehicle {
    private String category;

    public Motorcycle(String id, String brand, String model, int year, double price, String category) {
        super(id, brand, model, year, price);
        this.category = category;
    }



    @Override
    public String getType() {
        return "Motorcycle";
    }

    @Override
    public String toCSV() {
        return super.toCSV() + category + ";";
    }
}
