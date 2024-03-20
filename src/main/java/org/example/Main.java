package org.example;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        IVehicleRepository repository = new VehicleRepository("src/main/java/org/example/vehicles.csv");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wybierz działanie:");
        System.out.println("1 - Ustaw nowe hasło");
        System.out.println("2 - Wpisz istniejące hasło");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consuming the newline character

        switch (choice) {
            case 1:
                System.out.println("Ustaw nowe hasło:");
                String newPassword = scanner.nextLine();
                String hashedPassword = Hasher.hashPassword(newPassword);
                savePassword(hashedPassword);
                break;
            case 2:
                System.out.println("Podaj hasło:");
                String inputPassword = scanner.nextLine();
                if (checkPassword(inputPassword)) {
                    System.out.println("Hasło poprawne!");
                    System.out.println("Wybierz działanie:");
                    System.out.println("1 - Wypożycz pojazd");
                    int action = scanner.nextInt();
                    scanner.nextLine(); // Consuming the newline character
                    switch (action) {
                        case 1:
                            System.out.println("Podaj numer ID pojazdu, który chcesz wypożyczyć:");
                            String vehicleId = scanner.nextLine();
                            repository.rentCar(vehicleId);
                            break;
                        default:
                            System.out.println("Nieprawidłowy wybór.");
                    }
                } else {
                    System.out.println("Nieprawidłowe hasło!");
                }
                break;
            default:
                System.out.println("Nieprawidłowy wybór.");
        }

        System.out.println(repository.getVehicles());
    }

    private static void savePassword(String hashedPassword) {
        try (PrintWriter pw = new PrintWriter("src/main/java/org/example/passwd.csv")) {
            pw.println(hashedPassword + ";");
            System.out.println("Hasło zostało pomyślnie ustawione.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkPassword(String inputPassword) {
        try (Scanner scfile = new Scanner(new File("src/main/java/org/example/passwd.csv"))) {
            return Hasher.hashPassword(inputPassword).equals(scfile.nextLine().split(";")[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}