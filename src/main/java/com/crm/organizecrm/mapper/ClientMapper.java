package com.crm.organizecrm.mapper;

import com.crm.organizecrm.dto.ClientDTO;
import com.crm.organizecrm.model.Client;

public class ClientMapper {

    public static ClientDTO toDTO(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .contactId(client.getContact() != null ? client.getContact().getId() : null)
                .companyId(client.getCompany() != null ? client.getCompany().getId() : null)
                .build();
    }

    public static Client toEntity(ClientDTO clientDTO) {
        return Client.builder()
                .id(clientDTO.getId())
                .name(clientDTO.getName())
                .email(clientDTO.getEmail())
                .phone(clientDTO.getPhone())
                .build();
    }
}
