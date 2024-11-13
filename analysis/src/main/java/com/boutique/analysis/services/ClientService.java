package com.boutique.analysis.services;

import com.boutique.analysis.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<Client> getAllClients();
    
    Optional<Client> getClientById(int clientId);
    
    Client addClient(Client newClient);
    
    Client updateClient(Client updatedClient);
    
    void deleteClient(int clientId);
}
