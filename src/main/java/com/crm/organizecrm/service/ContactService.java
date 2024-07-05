package com.crm.organizecrm.service;

import com.crm.organizecrm.dto.ContactDTO;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    ContactDTO createContact(ContactDTO contactDTO);
    ContactDTO updateContact(Long id, ContactDTO contactDTO);
    void deleteContact(Long id);
    Optional<ContactDTO> getContactById(Long id);
    List<ContactDTO> getAllContacts();
}
