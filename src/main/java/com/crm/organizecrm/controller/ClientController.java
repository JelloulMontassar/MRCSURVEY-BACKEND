package com.crm.organizecrm.controller;

import com.crm.organizecrm.model.Client;
import com.crm.organizecrm.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.createClient(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        return ResponseEntity.ok(clientService.updateClient(id, client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Client>> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }
}
