package com.crm.organizecrm.controller;

import com.crm.organizecrm.model.Contact;
import com.crm.organizecrm.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.createContact(contact));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.updateContact(id, contact));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Contact>> getContactById(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.getContactById(id));
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }
}
