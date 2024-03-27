package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
class UserRepository implements IUserRepository {
    private List<User> users;
    private String filePath;

    public UserRepository(String filePath) {
        this.filePath = filePath;
        this.users = new ArrayList<>();
        loadUsers();
    }

    private void loadUsers() {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(";");
                if (data.length == 3) {
                    User user = new User(data[0], data[1], "", data[2]); // Tworzymy użytkownika z loginem, hasłem, pustym polem roli i wypożyczonym pojazdem
                    users.add(user);
                } else {
                    System.err.println("Niepoprawny format danych w pliku CSV: " + Arrays.toString(data));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }




    @Override
    public User getUser(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void save() {
        try (PrintWriter pw = new PrintWriter(new File(filePath))) {
            for (User user : users) {
                pw.println(user.toCsvString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRentedVehicleID(String login, String rentedVehicleID) {
        List<String> fileContent = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 2 && parts[0].equals(login)) {
                    parts[2] = rentedVehicleID;
                    line = String.join(";", parts);
                }
                fileContent.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (String line : fileContent) {
                pw.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}