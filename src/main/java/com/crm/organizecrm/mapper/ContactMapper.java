package com.crm.organizecrm.mapper;

import com.crm.organizecrm.dto.ContactDTO;
import com.crm.organizecrm.model.Contact;

public class ContactMapper {

    public static ContactDTO toDTO(Contact contact) {
        return ContactDTO.builder()
                .id(contact.getId())
                .name(contact.getName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .clientId(contact.getClient() != null ? contact.getClient().getId() : null)
                .build();
    }

    public static Contact toEntity(ContactDTO contactDTO) {
        return Contact.builder()
                .id(contactDTO.getId())
                .name(contactDTO.getName())
                .email(contactDTO.getEmail())
                .phone(contactDTO.getPhone())
                .build();
    }
}
