package com.boutique.analysis.controllers;

import com.boutique.analysis.models.Client;
import com.boutique.analysis.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable int id) {
        Optional<Client> client = clientService.getClientById(id);
        return client.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/add")
    public ResponseEntity<Client> addClient(@RequestBody Client newClient) {
        Client addedClient = clientService.addClient(newClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedClient);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable int id, @RequestBody Client updatedClient) {
        updatedClient.setClientId(id); 
        Client updated = clientService.updateClient(updatedClient);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok("Client deleted successfully.");
    }
}
