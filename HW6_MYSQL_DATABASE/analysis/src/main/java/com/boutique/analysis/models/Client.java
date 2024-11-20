package com.boutique.analysis.models;

public class Client {

    private int clientId;
    private String clientName;
    private String email;
    private String phoneNumber;

    public Client() {}

    public Client(int clientId, String clientName, String email, String phoneNumber) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
