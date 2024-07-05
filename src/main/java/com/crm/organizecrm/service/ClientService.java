package com.crm.organizecrm.service;

import com.crm.organizecrm.dto.ClientDTO;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    ClientDTO createClient(ClientDTO clientDTO);
    ClientDTO updateClient(Long id, ClientDTO clientDTO);
    void deleteClient(Long id);
    Optional<ClientDTO> getClientById(Long id);
    List<ClientDTO> getAllClients();
}
