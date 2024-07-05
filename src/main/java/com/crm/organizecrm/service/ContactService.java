package com.crm.organizecrm.service;

import com.crm.organizecrm.model.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    Contact createContact(Contact contact);
    Contact updateContact(Long id, Contact contact);
    void deleteContact(Long id);
    Optional<Contact> getContactById(Long id);
    List<Contact> getAllContacts();
}
