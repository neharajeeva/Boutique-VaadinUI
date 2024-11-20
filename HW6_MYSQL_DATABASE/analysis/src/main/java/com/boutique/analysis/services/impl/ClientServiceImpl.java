package com.boutique.analysis.services.impl;

import com.boutique.analysis.entities.ClientEntity;
import com.boutique.analysis.models.Client;
import com.boutique.analysis.repositories.ClientRepository;
import com.boutique.analysis.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClients() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false) // Convert Iterable to Stream
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Client> getClientById(int clientId) {
        return clientRepository.findById(clientId)
                .map(this::convertToModel);
    }

    @Override
    public Client addClient(Client newClient) {
        ClientEntity savedEntity = clientRepository.save(convertToEntity(newClient));
        return convertToModel(savedEntity);
    }

    @Override
    public Client updateClient(Client updatedClient) {
        ClientEntity savedEntity = clientRepository.save(convertToEntity(updatedClient));
        return convertToModel(savedEntity);
    }

    @Override
    public void deleteClient(int clientId) {
        clientRepository.deleteById(clientId);
    }

    private Client convertToModel(ClientEntity entity) {
        if (entity == null) return null; // Null check
        return new Client(entity.getClientId(), entity.getClientName(), entity.getEmail(), entity.getPhoneNumber());
    }

    private ClientEntity convertToEntity(Client model) {
        if (model == null) return null; // Null check
        return new ClientEntity(model.getClientId(), model.getClientName(), model.getEmail(), model.getPhoneNumber());
    }
}
