package com.boutique.analysis.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;
    
    @Column(name = "client_name")
    private String clientName;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    public ClientEntity() {}

    public ClientEntity(int clientId, String clientName, String email, String phoneNumber) {
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
