package com.github.erikrz.contacts.service.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.github.erikrz.contacts.api.dto.request.CreateContactDto;
import com.github.erikrz.contacts.api.dto.response.ContactDto;
import com.github.erikrz.contacts.service.mapper.ContactMapper;
import com.github.erikrz.contacts.service.mapper.ContactMasker;
import com.github.erikrz.contacts.service.repository.ContactsRepository;
import com.querydsl.core.types.Predicate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.XSlf4j;

/**
 * Service that persists contacts.
 *
 * @author erikrz
 */
@XSlf4j
@RequiredArgsConstructor
public class ContactsServiceImpl implements ContactsService {

    private final ContactsRepository contactsRepository;
    private final ContactMapper contactMapper;
    private final ContactMasker contactMasker;

    @Override
    public ContactDto saveContact(CreateContactDto createContactDto) {
        log.entry(contactMasker.mask(createContactDto));
        var contactToPersist = contactMapper.toContact(createContactDto);
        var savedContact = contactsRepository.save(contactToPersist);
        var contact = contactMapper.toContactDto(savedContact);
        log.exit(contactMasker.mask(contact));
        return contact;
    }

    @Override
    public Page<ContactDto> getAllContacts(Pageable pageable) {
        log.entry(pageable);
        var contactsPage = contactsRepository.findAll(pageable)
                .map(contactMapper::toContactDto);
        log.exit(contactsPage.map(contactMasker::mask));
        return contactsPage;
    }

    @Override
    public Page<ContactDto> searchContacts(Predicate predicate, Pageable pageable) {
        log.entry(predicate, pageable);
        try {
            var contactsPage = contactsRepository.findAll(predicate, pageable)
                    .map(contactMapper::toContactDto);
            log.exit(contactsPage.map(contactMasker::mask));
            return contactsPage;
        } catch (PropertyReferenceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public Optional<ContactDto> getContactById(Long id) {
        log.entry(id);
        var contact = contactsRepository.findById(id)
                .map(contactMapper::toContactDto);
        log.exit(contact.map(contactMasker::mask));
        return contact;
    }

    @Override
    public Optional<ContactDto> updateContactById(Long id, CreateContactDto updatedContactDto) {
        log.entry(id, contactMasker.mask(updatedContactDto));
        var updatedContact = contactsRepository.findById(id)
                .map(contactToUpdate -> {
                    contactMapper.updateFromCreateContactDto(updatedContactDto, contactToUpdate);
                    return contactsRepository.save(contactToUpdate);
                })
                .map(contactMapper::toContactDto);
        log.exit(updatedContact.map(contactMasker::mask));
        return updatedContact;
    }

    @Override
    public Optional<ContactDto> deleteContactById(Long id) {
        log.entry(id);
        var deletedContact = contactsRepository.findById(id)
                .map(contact -> {
                    contactsRepository.deleteById(contact.getId());
                    return contactMapper.toContactDto(contact);
                });
        log.exit(deletedContact.map(contactMasker::mask));
        return deletedContact;
    }

}
