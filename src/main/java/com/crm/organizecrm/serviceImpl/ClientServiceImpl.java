package com.crm.organizecrm.serviceImpl;

import com.crm.organizecrm.dto.ClientDTO;
import com.crm.organizecrm.exception.ResourceNotFoundException;
import com.crm.organizecrm.mapper.ClientMapper;
import com.crm.organizecrm.model.Client;
import com.crm.organizecrm.repository.ClientRepository;
import com.crm.organizecrm.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = ClientMapper.toEntity(clientDTO);
        return ClientMapper.toDTO(clientRepository.save(client));
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));
        existingClient.setName(clientDTO.getName());
        existingClient.setEmail(clientDTO.getEmail());
        existingClient.setPhone(clientDTO.getPhone());
        return ClientMapper.toDTO(clientRepository.save(existingClient));
    }

    @Override
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));
        clientRepository.delete(client);
    }

    @Override
    public Optional<ClientDTO> getClientById(Long id) {
        return clientRepository.findById(id).map(ClientMapper::toDTO);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(ClientMapper::toDTO)
                .collect(Collectors.toList());
    }
}
