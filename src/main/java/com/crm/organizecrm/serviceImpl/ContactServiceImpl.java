package com.crm.organizecrm.serviceImpl;

import com.crm.organizecrm.dto.ContactDTO;
import com.crm.organizecrm.exception.ResourceNotFoundException;
import com.crm.organizecrm.mapper.ContactMapper;
import com.crm.organizecrm.model.Contact;
import com.crm.organizecrm.repository.ContactRepository;
import com.crm.organizecrm.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public ContactDTO createContact(ContactDTO contactDTO) {
        Contact contact = ContactMapper.toEntity(contactDTO);
        return ContactMapper.toDTO(contactRepository.save(contact));
    }

    @Override
    public ContactDTO updateContact(Long id, ContactDTO contactDTO) {
        Contact existingContact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + id));
        existingContact.setName(contactDTO.getName());
        existingContact.setEmail(contactDTO.getEmail());
        existingContact.setPhone(contactDTO.getPhone());
        return ContactMapper.toDTO(contactRepository.save(existingContact));
    }

    @Override
    public void deleteContact(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + id));
        contactRepository.delete(contact);
    }

    @Override
    public Optional<ContactDTO> getContactById(Long id) {
        return contactRepository.findById(id).map(ContactMapper::toDTO);
    }

    @Override
    public List<ContactDTO> getAllContacts() {
        return contactRepository.findAll().stream()
                .map(ContactMapper::toDTO)
                .collect(Collectors.toList());
    }
}
