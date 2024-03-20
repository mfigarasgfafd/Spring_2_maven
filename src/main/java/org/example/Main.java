package org.example;

public class Main {
    public static void main(String[] args) {
        IVehicleRepository repository = new VehicleRepository("/home/student/IdeaProjects/untitled/src/vehicles.csv");

        repository.rentCar("1");

        repository.rentCar("2");

        repository.returnCar("1");

        repository.rentCar("3");

        repository.returnCar("3");
        System.out.println(repository.getVehicles());

    }

}