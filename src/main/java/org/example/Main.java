package org.example;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        IVehicleRepository vehicleRepository = new VehicleRepository("src/main/java/org/example/vehicles.csv");
        IUserRepository userRepository = new UserRepository("src/main/java/org/example/users.csv");
        Authentication authentication = new Authentication(userRepository);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wybierz działanie:");
        System.out.println("1 - Ustaw nowe hasło");
        System.out.println("2 - Wpisz istniejące hasło");
        int choice = scanner.nextInt();
        scanner.nextLine(); // newline char

        switch (choice) {
            case 1:
                System.out.println("Podaj login:");
                String newLogin = scanner.nextLine();
                System.out.println("Ustaw nowe hasło:");
                String newPassword = scanner.nextLine();
                String hashedPassword = Hasher.hashPassword(newPassword);
                savePassword(newLogin, hashedPassword, "none");
                break;
            case 2:
                System.out.println("Podaj login:");
                String login = scanner.nextLine();
                System.out.println("Podaj hasło:");
                String inputPassword = scanner.nextLine();
                if (authentication.authenticate(login, inputPassword)) {
                    System.out.println("Hasło poprawne!");


                    System.out.println("Wybierz działanie:");
                    System.out.println("1 - Wypożycz pojazd");
                    System.out.println("2 - Zwróć pojazd");
                    int action = scanner.nextInt();
                    scanner.nextLine(); // newline character
                    switch (action) {
                        case 1: // Wypożycz
                            System.out.println("Podaj numer ID pojazdu, który chcesz wypożyczyć:");
                            String vehicleId = scanner.nextLine();
                            vehicleRepository.rentCar(vehicleId);
                            // Update rented vehicle ID for the user
                            userRepository.updateRentedVehicleID(login, vehicleId);
                            break;
                        case 2: // Zwróć
                            System.out.println("Podaj numer ID pojazdu, który chcesz zwrócić:");
                            String returnedVehicleId = scanner.nextLine();
                            vehicleRepository.returnCar(returnedVehicleId);
                            // Update rented
                            userRepository.updateRentedVehicleID(login, "none");
                            break;
                        default:
                            System.out.println("Nieprawidłowy wybór.");
                    }


                } else {
                    System.out.println("Nieprawidłowe dane logowania!");
                }
                break;
            default:
                System.out.println("Nieprawidłowy wybór.");
        }
    }



    private static void savePassword(String login, String hashedPassword, String rentedVehicleID) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("src/main/java/org/example/users.csv", true))) {
            pw.println(login + ";" + hashedPassword + ";" + rentedVehicleID);
            System.out.println("Hasło zostało pomyślnie ustawione.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}