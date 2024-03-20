package org.example;

// Klasa User
class User {
    private String login;
    private String password;
    private String role;
    private String rentedVehicleId; // ID wypożyczonego pojazdu

    public User(String login, String password, String role, String rentedVehicleId) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.rentedVehicleId = rentedVehicleId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRentedVehicleId() {
        return rentedVehicleId;
    }

    public void setRentedVehicleId(String rentedVehicleId) {
        this.rentedVehicleId = rentedVehicleId;
    }

    // Metoda CSV
    public String toCsvString() {
        return login + "," + password + "," + role + "," + rentedVehicleId;
    }
}
